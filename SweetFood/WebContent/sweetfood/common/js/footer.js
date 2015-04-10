var rtime = new Date(1, 1, 2000, 12,00,00);
	var timeout = false;
	var delta = 400;

	function disableSwipe(elem) {
		elem.swipe({
			swipe: function(event, direction, distance, duration, fingerCount) {
				event.preventDefault();
			}
		});

		elem.children().each(function() {
			disableSwipe($(this));
		});
	}

	$(document).ready(function() {
		
		$('#pricing .contact_next').disableSelection()
		$('#main-content').height($(window).height());
		if ($(window).width() > 480) {
			$('#main-content, #main-content .wrap').css({
				overflow: "hidden"
			});
		}

		$('.next').bind('touchstart', function(evt) { $(this).click(); });
		$('.next').bind('click', function() {
			position++;
			scrollPage(false);
		});

		$('.next-slide, .prev-slide').bind('touchstart', function(evt) { $(this).click(); });
		$('.pager').find('ul').find('li').each(function() {
			$(this).find('a').bind('touchstart', function() { $(this).click(); });
		});

		disableSwipe($('#orientation'));
		disableSwipe($('#header'));
		setSwipe();

		$(document).keydown(function(e){
			if (motion) {
			    if (e.keyCode == 37 || e.keyCode == 38 ||e.keyCode == 39 || e.keyCode == 40) {
				    motion = false;
					var element = $('.inner-wrap').children('div:nth-child(' + (position + 1) + ')');

					if (e.keyCode == 38 || e.keyCode == 40) {
						if(e.keyCode == 40){
							position++;
							if(position > 6){
								position = 6;
							}
						} else {
							position--;
							if(position < 0){
								position = 0;
							}
						}

						if (position == 0 && prev_position == -1) position++;
						if (position != prev_position) {
							scrollPage(false);
						} else {
							motion = true;
						}
					} else {
						if (element.hasClass('sliding')) {
							sliderPosition = element.attr('data-position');
							if (e.keyCode == 39) {
								sliderPosition++;
								if (sliderPosition > element.find('.carousel-inner').children('div').length) {
									position++;
									element.attr('data-position', sliderPosition - 1);
									scrollPage(false);
								} else {
									changeSlide(element.attr('id'), sliderPosition);
								}
							} else {
								sliderPosition--;
								if (sliderPosition < 1) {
									position--;
x
									element.attr('data-position', 1);
									scrollPage(false);
								} else {
									changeSlide(element.attr('id'), sliderPosition);
								}
							}
						} else {
							motion = true;
						}
					}
			       	return false;
			    }
			}
		});
		$('.next-slide').each(function() {
			$(this).live('click', function() {
				var id = $(this).parent().parent().attr('id');
				var item = $(this).parent().find('.pager').first().find('li.active').index() + 2;
				if (motion) changeSlide(id, item);
			});
		});
		$('.prev-slide').each(function() {
			$(this).live('click', function() {
				var id = $(this).parent().parent().attr('id');
				var item = $(this).parent().parent().find('.pager').first().find('li.active').index();
				if (motion) changeSlide(id, item);
			});
		});
		$('#pricing').find('.others').first().find('li').each(function() {
			$(this).find('input[type=checkbox]').each(function() {
				var span = $('<span class="' + $(this).attr('type') + '"></span>').click(doCheck).mousedown(doDown).mouseup(doUp);
				if ($(this).is(':checked')) {
		            span.addClass('checked');
		        }
		        $(this).wrap(span).hide();
		        $(this).change(processAmount);
			});
			$(this).bind('touchstart', function() { $(this).find('input[type=checkbox]').first().click(); });
			$(this).find('label').bind('click', function() {
				$(this).parent().find('input[type=checkbox]').first().click();
			});
		});
		$( "#slider-range-computer" ).slider({
            range: "min",
            value: 1,
            min: 1,
            max: 50,
            slide: function( event, ui ) {
            	$( "#slider-range-computer" ).parent().find('.value').first().text(ui.value);
            },
            change: processAmount
        });

		$( "#slider-range-user" ).slider({
            range: "min",
            value: 1,
            min: 1,
            max: 50,
            slide: function( event, ui ) {
            	$( "#slider-range-user" ).parent().find('.value').first().text(ui.value);
            },
            change: processAmount
        });
		$( "#slider-range-server" ).slider({
            range: "min",
            value: 1,
            min: 1,
            max: 10,
            slide: function( event, ui ) {
            	$( "#slider-range-server" ).parent().find('.value').first().text(ui.value);
            },
            change: processAmount
        });

		processAmount();
		$('#exo_logo').live('click', function() {
			position = 0;
			scrollPage(true);
		});
		$('#header').find('nav').first().find('ul').find('li').each(function() {
			$(this).each(function() {
				$(this).find('a').click(function(event) {
					event.preventDefault();
					position = $(this).parent().index();
					scrollPage(true);
				});
			});
		});

		if (!window.addEventListener) {
		    window.attachEvent("onhashchange", hashChangeListener);
		} else {
		    window.addEventListener("hashchange", hashChangeListener, false);
		}

		function resizeend() {
		    if (new Date() - rtime < delta) {
		        setTimeout(resizeend, delta);
		    } else {
		        timeout = false;

		        positionWhatBackground();
				$('#main-content').scrollTop(0);
				var windowheight = $(window).height();
				$('#main-content').height(windowheight);

				adjustOffsetData();
				positionPager();
				setTimeout(function() {
					scrollPage(false);

					$('.sliding').each(function() {
						var item = $(this).find('.carousel:first').find('.carousel-inner:first').find('.pager:first').find('li.active').index();
						var slider = $(this).find('.carousel').first();
						adjustSlider($(this), slider, item);
					});
				}, 300);
		    }
		}
		$(window).resize(function() {
				rtime = new Date();
			    if (timeout === false) {
			        timeout = true;
			        setTimeout(resizeend, delta);
		    }
		});

		adjustOffsetData();

		$('#b_carousel').carousel({ interval: false });
		$('#b_carousel').carousel('pause');
		$('#b_carousel').carousel('next');

		$('#f_carousel').carousel({ interval: false });
		$('#f_carousel').carousel('next');
		$('#f_carousel').carousel('pause');

		$('#o_carousel').carousel({ interval: false });
		$('#o_carousel').carousel('next');
		$('#o_carousel').carousel('pause');

		$('.anchorClass').mousewheel(function(event, delta, deltaX, deltaY) {
			if (motion) {
				motion = false;

				event.preventDefault();
				if ($(this).hasClass('sliding')) {
					var element = $(this);
					sliderPosition = element.attr('data-position');
					if (deltaY < 0) {
						sliderPosition++;
						if (sliderPosition > element.find('.carousel-inner').children('div').length) {
							position++;
							element.attr('data-position', sliderPosition - 1);
							scrollPage(false);
						} else {
							changeSlide(element.attr('id'), sliderPosition);
						}
					} else if(deltaY > 0) {
						sliderPosition--;
						if (sliderPosition < 1) {
							position--;
							element.attr('data-position', 1);
							scrollPage(false);
						} else {
							changeSlide(element.attr('id'), sliderPosition);
						}
					} else {
						motion = true;
					}
				} else {
					if(deltaY < 0){
						position++;
						if(position > 6){
							position=6;
						}
					} else if (deltaY > 0) {
						position--;
						if(position < 0){
							position = 0;
						}
					}

					if (position == 0 && prev_position == -1) position++;
					if (position != prev_position) {
						scrollPage(false);
					} else {
						motion = true;
					}
				}
			}
		});

		if ($(window).width() > 480) {
			positionWhatBackground();
			setTimeout(function() {
				var uri = window.location.hash;
				anchor = $( "a[href$='"+uri+"']" ).data('anchor');
				if (anchor != null && anchor != '') {
					position = $('#' + anchor).index();
				}
				scrollPage(false);
				adjustNavigation();
			}, 300);
			setTimeout(function() { positionPager(); }, 1000);
		}
	});