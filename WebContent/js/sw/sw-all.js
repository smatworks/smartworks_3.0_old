$(document).ready(function() {
	$('.js_tab_work a').swnavi({
		target: 'my_works',
		after: function(event) {
			$(event.target).addClass('current').siblings().removeClass('current');
		}
	});
	
	$('.js_tab_com a').swnavi({
		target: 'my_communities',
		after: function(event) {
			$(event.target).addClass('current').siblings().removeClass('current');
		}
	});
	
	$('a.js_content').swnavi({target: 'content'});
	
	$('.notice_count_icon a').swnavi({
		target: 'notice_message_box', 
		before: function(event) {
			$('#notice_message_box').hide();
			$('#notice_message_box').slideDown();
		},
		after: function(event) {
			$('.notice_count_icon').find('a').removeClass('current');
			$(event.target).parents('.notice_count_icon:first').find('a').addClass('current');
		}
	});
	
	$('.close_message_box').live('click', function(e){
		$('#notice_message_box').slideUp();
		setTimeout(function() {
			$('.notice_count_icon').find('a').removeClass('current');
		},500);
		return false;
	});

	$('.top_menu').live('click', function(e){
		$('.top_menu').find('li').removeClass('current');
		$(e.target).parents('li:first').addClass('current');
	});

	$('.select_action').live('click', function(e){
		$('.select_action').siblings('.upload_form').hide();
		var targetId = $(e.target).parents('li:first').find('a').attr('id');
		$('#'+ targetId + '_box').show();
		return false;
	});
	
	$('.collapse_slide_handle').live('click', function(e) {
		$(e.target).parent().siblings('.collapsible').slideToggle(500); 
	});
	$('.collapse_handle').live('click', function(e) {
		$(e.target).siblings('.collapsible').toggle(); 
	});
	
	$('input.auto_complete').live('keyup', function(e) {
		var input = $(e.target);
		var target = input.parent().siblings('div');
		var url = input.attr('href');
		var lastValue = input[0].value;
		setTimeout(function() {
			var currentValue = input[0].value;
			
			if(lastValue === currentValue) {
				$.ajax({
					url: url,
					data: {key:input[0].value}, 
					context: input,
					success: function(data, status, jqXHR){
						target.show();
						target.html(data);
					}
				});
			} else {
			}
		},500);
	});
	
	$('input.auto_complete').live('focusout', function(e) {
		var input = $(e.target);
		var target = input.parent().siblings('div');
		setTimeout(function() {
			input[0].value = '';
			target.hide();
		},500);
	});
	
	var lastCatTarget = null;
	var lastGroupTarget = null;
	$('.drill_down').live('click', function(e){
		var input = $(e.target);
		var target = input.siblings('div');
		var url = input.attr('targetContent');
		var categoryId = input[0].getAttribute("categoryId");
		var groupId = input[0].getAttribute("groupId");
		if(lastCatTarget !=null && categoryId != null){
			lastCatTarget.slideToggle();
			lastCatTarget = null;
		}else if(lastGroupTarget != null && groupId != null){
			lastGroupTarget.slideToggle();
			lastGroupTarget = null;
		}
		if(url == 'undefined' || (categoryId==null && groupId==null)){
			return;
		}
		$.ajax({ 
			url: url,
			data: {categoryId: categoryId,
				   groupId: groupId},
			context: input,
			success: function(data, status, jqXHR){
				target.slideToggle();
				target.html(data);
				if(categoryId != null) lastCatTarget = target;
				else if(groupId != null) lastGroupTarget = target;
			}
		});
	});	
});	