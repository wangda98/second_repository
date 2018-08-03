var MyModal2 = (function() {
	function modal(fn) {
		this.fn = fn; //点击确定后的回调函数
		this._addClickListen();
	}
	modal.prototype = {
		show: function() {
			$('.m-modal2').fadeIn(100);
			$('.m-modal2').children('.m-modal-dialog2').animate({
				"margin-top2": "30px"
			}, 50);
		},
		_addClickListen: function() {
			var that = this;
			$(".m-modal2").find('*').on("click", function(event) {
				event.stopPropagation(); //阻止事件冒泡
			});
			$(".m-modal2,.m-modal-close2,.m-btn-cancel2").on("click", function(event) {
				that.hide();
			});
			$(".m-btn-sure2").on("click", function(event) {
				that.fn();
				that.hide();
			});
		},
		hide: function() {
			var $modal = $('.m-modal2');
			$modal.children('.m-modal-dialog2').animate({
				"margin-top2": "-100%"
			}, 10);
			$modal.fadeOut(100);
		}

	};
	return {
		modal: modal
	}
})();