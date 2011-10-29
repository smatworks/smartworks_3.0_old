/*
 * SWNavi - jQuery plugin for supporting ajax navigation
 *
 * Copyright (c) 2010 SW-Factory, Inc.
 *
 *
 * Revision: $Id: sw-nav.js,v 1.4 2011/10/26 13:12:38 ysjung Exp $
 *
 */

/**
 */

(function($){
	var target_ = null;
	var origin_ = null;

	var historic_load = function(url) {
		if(url) {
			if(origin_ == null) {
				origin_ = $('#' + target_).html() || "";
			}
			$('#' + target_).load(url);
		} else {
			$('#' + target_).html(origin_);
		}
	};
	
	var methods = {
		init : function(options) {
			var ops = $.extend({
				history: false,
				target: 'content',
				before: null,
				after: null
			}, options);

			if(ops.history) {
				target_ = ops.target;
				$.history.init(historic_load);
			}
			this.live('click', ops, methods.move);
			return this;
		},
		move : function(event) {
			var options = event.data;
			var target = options.target;
			var before = options.before;
			var after = options.after;
			try {
				if(before) {
					before.apply(this, [event]);
				}
				if(target == target_) {
					$.history.load(this.getAttribute('href'));
				} else {
					$('#' + target).load(this.getAttribute('href'));
				}
				if(after) {
					after.apply(this, [event]);
				}
			} catch(err) {
				console.log(err);
			}
			
			return false;
		}
	};
	
	$.fn.swnavi = function(method) {
		if(methods[method]) {
			return methods[method].apply(this, Array.prototype.slice.call(arguments, 1));
		} else if (typeof method === 'object' || !method) {
			return methods.init.apply(this, arguments);
		} else {
			$.error('Method ' + method + ' does not exist on jQuery.swnavi');
		}
	};
})(jQuery);
