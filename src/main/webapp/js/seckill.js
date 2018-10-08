/**
 * 使用json封装方式
 */
var seckill = {
	//封装相关ajax的URL
	URL: {
		now: function(){
			return '/seckill/time/now';
		},
		exposer: function(seckillId){
			return '/seckill/' + seckillId + '/exposer';
		},
		execute: function(seckillId, md5){
			return '/seckill/' + seckillId + '/' + md5 + '/execute';
		}
	},
	
	//手机验证
	validatePhone: function(phone) {
		if(phone && phone.length == 11 && !isNaN(phone)){
			return true;
		}else {
			return false;
		}
	},
	
	//详情页秒杀逻辑
	detail: {
		init: function(params){
			var userPhone = $.cookie('userPhone');
			if(!seckill.validatePhone(userPhone)){
				//手机号验证不通过，要进行手机号输入（相当于登录）
				var seckillPhoneModal = $('#killPhoneModal');
				seckillPhoneModal.modal({
					show: true,	//显示弹出层
					backdrop: 'static', //禁止位置关闭
					keyboard: false   //关闭键盘事件
				});
				$('#killPhoneBtn').click(function(){
					var inputPhone = $('#killPhoneKey').val();
					//console.log("inputPhone: " + inputPhone);
					if(seckill.validatePhone(inputPhone)){
						$.cookie('userPhone', inputPhone, {expires: 7, path: '/seckill'});
						window.location.reload();
					}else{
						$('#killPhoneMessage').hide().html('<label class="label label-danger">手机号错误！请重新输入...</label>').show(200);
					}
				});
			}
			//手机号从cookie中拿取验证通过
			var seckillId = params['seckillId'];
			var startTime = params['startTime'];
			var endTime = params['endTime'];
			$.get(seckill.URL.now(), {}, function (result) {
				if(result && result['success']){
					var nowTime = result['data'];
					seckill.countDown(seckillId, nowTime, startTime, endTime);
				} else {
					//console.log("result : " + result);
					alert("result : " + result)
				}
			});
				
		}
	},
	handlerSeckill: function(seckillId, node){
		node.hide().html('<button class="btn btn-primary btn-lg" id="killBtn">开始秒杀</button>');
		$.post(seckill.URL.exposer(seckillId), {}, function(result){
			if(result && result['success']){
				var exposer = result['data'];
				//秒杀开启
				if(exposer['exposed']){
					var md5 = exposer['md5'];
					var killUrl = seckill.URL.execute(seckillId, md5);
					//console.log("killUrl: " + killUrl);
					$('#killBtn').on('click', function(){
						$(this).addClass('disabled');     //先禁用按钮
						$.post(killUrl, {}, function(result){
							if(result && result['success']){
								var killResult = result['data'];
								var state = killResult['state'];
								var stateInfo = killResult['stateInfo'];
								if(state == 1){	//秒杀成功
									node.html('<span class="label label-success">' + stateInfo + '</span>');
								}else if(state == 0){  //秒杀结束
									node.html('<span class="label label-info">' + stateInfo + '</span>');
								} else if(state == -1){  //秒杀重复
									node.html('<span class="label label-warning">' + stateInfo + '</span>');
								} else if(state == -2){   //系统异常
									node.html('<span class="label label-danger">' + stateInfo + '</span>');
								} else if(state == -3){   //数据篡改
									node.html('<span class="label label-danger">' + stateInfo + '</span>');
								}
							}
						});
					});
					node.show();
				} else {  //未开启秒杀
					var now = exposer['now'];
					var start = exposer['start'];
					var end = exposer['end'];
					seckill.countDown(seckillId, nowTime, startTime, endTime);
				}
			} else {
				console.log("result: " + result);
			}
		});
	},
	countDown: function(seckillId, nowTime, startTime, endTime){
		//console.log(seckillId + '__' + nowTime + '__' + startTime + '__' + endTime);
		var seckillBox = $('#seckill-box');
		if(nowTime > endTime){
			seckillBox.html('此商品秒杀活动已结束！');
		} else if(nowTime < startTime){
			//活动未开始，计时事件绑定
			var killTime = new Date(startTime + 1000);
			seckillBox.countdown(killTime).on('update.countdown', function(event){
				//event.strftime - 格式化函数
				var format = event.strftime('秒杀倒计时：%D天  %H时  %D分  %S秒  ');
				seckillBox.html(format);
			}).on('finish.countdown', function(){
				//时间完成后进行回调
				//console.log('finish.countdown,Second killing activity start --- ');
				seckill.handlerSeckill(seckillId, seckillBox);
			});
		} else {
			//秒杀活动开始
			//console.log('Second killing activity start --- ');
			seckill.handlerSeckill(seckillId, seckillBox);
		}
	}
}