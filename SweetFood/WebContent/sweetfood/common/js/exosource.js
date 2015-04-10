//place any jQuery/helper plugins in here, instead of separate, slower script files.
jQuery.extend(jQuery.easing,{easeInOutExpo:function(a,b,c,d,e){if(b==0){return c}if(b==e){return c+d}if((b/=e/2)<1){return d/2*Math.pow(2,10*(b-1))+c}return d/2*(-Math.pow(2,-10*--b)+2)+c}});

/**
�* jQuery.browser.mobile (http://detectmobilebrowser.com/)
�* jQuery.browser.mobile will be true if the browser is a mobile device
�**/
(function(a){jQuery.browser.mobile=/android.+mobile|avantgo|bada\/|blackberry|blazer|compal|elaine|fennec|hiptop|iemobile|ip(hone|od)|iris|kindle|lge |maemo|midp|mmp|netfront|opera m(ob|in)i|palm( os)?|phone|p(ixi|re)\/|plucker|pocket|psp|symbian|treo|up\.(browser|link)|vodafone|wap|windows (ce|phone)|xda|xiino/i.test(a)||/1207|6310|6590|3gso|4thp|50[1-6]i|770s|802s|a wa|abac|ac(er|oo|s\-)|ai(ko|rn)|al(av|ca|co)|amoi|an(ex|ny|yw)|aptu|ar(ch|go)|as(te|us)|attw|au(di|\-m|r |s )|avan|be(ck|ll|nq)|bi(lb|rd)|bl(ac|az)|br(e|v)w|bumb|bw\-(n|u)|c55\/|capi|ccwa|cdm\-|cell|chtm|cldc|cmd\-|co(mp|nd)|craw|da(it|ll|ng)|dbte|dc\-s|devi|dica|dmob|do(c|p)o|ds(12|\-d)|el(49|ai)|em(l2|ul)|er(ic|k0)|esl8|ez([4-7]0|os|wa|ze)|fetc|fly(\-|_)|g1 u|g560|gene|gf\-5|g\-mo|go(\.w|od)|gr(ad|un)|haie|hcit|hd\-(m|p|t)|hei\-|hi(pt|ta)|hp( i|ip)|hs\-c|ht(c(\-| |_|a|g|p|s|t)|tp)|hu(aw|tc)|i\-(20|go|ma)|i230|iac( |\-|\/)|ibro|idea|ig01|ikom|im1k|inno|ipaq|iris|ja(t|v)a|jbro|jemu|jigs|kddi|keji|kgt( |\/)|klon|kpt |kwc\-|kyo(c|k)|le(no|xi)|lg( g|\/(k|l|u)|50|54|e\-|e\/|\-[a-w])|libw|lynx|m1\-w|m3ga|m50\/|ma(te|ui|xo)|mc(01|21|ca)|m\-cr|me(di|rc|ri)|mi(o8|oa|ts)|mmef|mo(01|02|bi|de|do|t(\-| |o|v)|zz)|mt(50|p1|v )|mwbp|mywa|n10[0-2]|n20[2-3]|n30(0|2)|n50(0|2|5)|n7(0(0|1)|10)|ne((c|m)\-|on|tf|wf|wg|wt)|nok(6|i)|nzph|o2im|op(ti|wv)|oran|owg1|p800|pan(a|d|t)|pdxg|pg(13|\-([1-8]|c))|phil|pire|pl(ay|uc)|pn\-2|po(ck|rt|se)|prox|psio|pt\-g|qa\-a|qc(07|12|21|32|60|\-[2-7]|i\-)|qtek|r380|r600|raks|rim9|ro(ve|zo)|s55\/|sa(ge|ma|mm|ms|ny|va)|sc(01|h\-|oo|p\-)|sdk\/|se(c(\-|0|1)|47|mc|nd|ri)|sgh\-|shar|sie(\-|m)|sk\-0|sl(45|id)|sm(al|ar|b3|it|t5)|so(ft|ny)|sp(01|h\-|v\-|v )|sy(01|mb)|t2(18|50)|t6(00|10|18)|ta(gt|lk)|tcl\-|tdg\-|tel(i|m)|tim\-|t\-mo|to(pl|sh)|ts(70|m\-|m3|m5)|tx\-9|up(\.b|g1|si)|utst|v400|v750|veri|vi(rg|te)|vk(40|5[0-3]|\-v)|vm40|voda|vulc|vx(52|53|60|61|70|80|81|83|85|98)|w3c(\-| )|webc|whit|wi(g |nc|nw)|wmlb|wonu|x700|xda(\-|2|g)|yas\-|your|zeto|zte\-/i.test(a.substr(0,4))})(navigator.userAgent||navigator.vendor||window.opera);

var offsetArray = new Array();
var motion = true;
var position = 0;
var prev_position = -1;
var sliderPosition = 0;
var coordY = 0;
var coordX = 0;
var sliding = false;
var touchMove = false;


function adjustOffsetData() {
	if ($(window).width() > 480) 
		$('.anchorClass').height($(window).height());
	else {
		$('#what-we-do').css({ height: 480 });
	}
	i = 0;
	$("body").find(".anchorClass").each( function(){
		offsetArray[i] = $(this).offset();
		i++;
	});
}

function processAmount() {
	var computers = $('#pricing').find('.computers').first().children('.value').text();
	var users = $('#pricing').find('.users').first().children('.value').text();
	var servers = $('#pricing').find('.servers').first().children('.value').text();
	var emails = 0;
	if ($('#pricing').find('#emails').first().attr('checked')) {
		emails = 25 + (10 * users);
	}
	var antivir = 0;
	if ($('#pricing').find('#antivirus').first().attr('checked')) {
		antivir = (10 * servers) + (5 * computers);
	}
	var backups = 0;
	if ($('#pricing').find('#cloud').first().attr('checked')) {
		backups = 25;
	}
	var hosting = 0;
	if ($('#pricing').find('#webhost').first().attr('checked')) {
		hosting = 25;
	}

	var amount = (computers * 25) + (users * 25) + (servers * 100) + emails + antivir + backups + hosting;

	$('#pricing').find('.total').first().find('.amount').first().find('.price').text(amount);
}

function doCheck() {
    if ($(this).hasClass('checked')) {
        $(this).removeClass('checked');
        $(this).parent().addClass('unckecked');
        $(this).parent().removeClass('checked');
        $(this).children('input:checkbox').removeAttr("checked");
    } else {
        $(this).addClass('checked');
        $(this).parent().removeClass('unckecked');
        $(this).parent().addClass('checked');
        $(this).children('input:checkbox').attr("checked", "checked");
    }
    processAmount();
}

function doDown() {
    $(this).addClass('clicked');
}

function doUp() {
    $(this).removeClass('clicked');
}

function hashChangeListener() {
	var lang = $('html').attr('lang').substr(0,2);
	var uri = window.location.hash;
	anchor = $( "a[href$='"+uri+"']" ).data('anchor');
	if (anchor != null && anchor != '') {
		position = $('#' + anchor).index();
	}
	setTimeout(function() {
		scrollPage(false);
	}, 200);
}

function scrollPage(scrollFirstSlide) {
	if ($(window).width() > 480) {
		$('#benefits, #features, #options').each(function() {
			$(this).find('.next-slide').first().css({right: ($(window).width() > 1300? '-100px': '-56px') });
			$(this).find('.prev-slide').first().css({left: ($(window).width() > 1300? '-100px': '-56px') });
		});

		prev_position = position;
		if(!$.browser.msie || ($.browser.msie && parseInt($.browser.version, 10) != 7)) {
			var element = $('nav').find('ul').first().find('li:nth-child(' + (position + 1) + ')').first().find('a');
			if (element.attr('href')) {
				var page = element.attr('href');
				if(element.data('page-title')){
					document.title = element.data('page-title');
				}else{
					document.title = 'Exosource';
				}
				if (typeof history.pushState != 'undefined') { 
					history.replaceState(null, element.data('page-title')+' | Exosource', page);  
				}
			}
		}
		
		$('#main-content').animate({
			scrollTop: offsetArray[position].top
		}, 800, 'easeInOutExpo', function() {
			if (position >= 2 && position <= 4) {
				if (position == 2) {
					if ($('#benefits').find('.pager .active').index() > 0) {
						showLeftArrow($('#benefits').find('.prev-slide').first());
					}
					if ($('#benefits').find('.pager .active').index() < $('#benefits').find('.pager').find('li').length - 1) {
						showRightArrow($('#benefits').find('.next-slide').first());
					}
				} else if (position == 3) {
					if ($('#features').find('.pager .active').index() > 0) {
						showLeftArrow($('#features').find('.prev-slide').first());
					}
					if ($('#features').find('.pager .active').index() < $('#benefits').find('.pager').find('li').length - 1) {
						showRightArrow($('#features').find('.next-slide').first());
					}
				} else if (position == 4) {
					if ($('#options').find('.pager .active').index() > 0) {
						showLeftArrow($('#options').find('.prev-slide').first());
					}
					if ($('#options').find('.pager .active').index() < $('#benefits').find('.pager').find('li').length - 1) {
						showRightArrow($('#options').find('.next-slide').first());
					}
				}
			}
			motion = true;
			if (scrollFirstSlide) {
				$('.sliding').each(function() {
					changeSlide($(this).attr('id'), 1);
				});
			}
		});
		adjustNavigation();
	} else {
		if (position < 2) {
			var distance = (prev_position == -1 ? 480 : (prev_position < position ? 480 : 0));
			$('#main-content').animate({
				scrollTop: distance
			}, 800, 'easeInOutExpo', function() {
				prev_position = position;
				motion = true;
			});
		}
	}
}

function setSwipe() {
	if (navigator.userAgent.match(/iPad/i) != null) {
		$('.sliding').swipe({
			swipe: function(event, direction, distance, duration, fingerCount) {
				if (direction == 'up' || direction == 'down') {
					position += (direction == 'up' ? 1 : -1);
					scrollPage(false);
				} else {
					var sliderPosition = $(this).find('.pager:first').find('li.active').index() + (direction == 'left' ? 2 : 0);
					if (sliderPosition > 0 && sliderPosition < $(this).find('.pager:first').find('li').length + 1)
						changeSlide($(this).attr('id'), sliderPosition);
					else {
						position += (direction == 'left' ? 1 : -1);
						scrollPage(false);
					}
				}
			},
			threshold: 150
		});
		$('.anchorClass').swipe({
			swipeUp: function(event, direction, distance, duration, fingerCount) {
				if (position < $('.anchorClass').length - 1) {
					position += 1;
					scrollPage(false);
				}
			},
			swipeDown: function(event, direction, distance, duration, fingerCount) {
				if (position > 0) {
					position -= 1;
					scrollPage(false);
				}
			},
			threshold: 150
		});
	} else {
		disableSwipe($('.sliding'));
		disableSwipe($('.anchorclass'));
	}
}

function adjustNavigation() {
	$('nav ul li a').each(function() {
		$(this).removeClass('active');
	});

	$list_item = $('nav ul li:nth-child(' + (position + 1) + ')');

        var target = $list_item.find('a').attr('rel');
        if (target.substring(0,1) != '/') {
            target = '/'+target;
        }

	$('#langswitch').attr('href', target)
	
	$('#back_active').animate({
		left: $list_item.offset().left - $('nav ul').offset().left
	}, 500, 'easeInOutExpo', function(){
		$list_item.find('a').attr('class', 'active');
	});
}

function positionWhatBackground() {
	if ($(window).width() > 480) {
		var distance = ($(window).width() > 1300 ? -1480 : -1280) + (($(window).width() / 2) + ($(window).width() > 1300 ? 530 : 465));
		$('#what-we-do').css({
			backgroundPosition:  (distance > 0 ? 0 : distance) + 'px 25%'
		});
	} else {
		$('#what-we-do').css({
			backgroundPosition: ($(window).width() > 320 ? '86px' : '23px') + ' 291px'
		});
	}
}

function changeSlide (parentId, item) {
	if (sliding == false) {
		sliding = true;
		
		sliderPosition = item;
		var element = $('#' + parentId);
		var slider = element.find('.carousel').first();
		var prev_item = element.attr('data-position');
		if (item != prev_item) {
			var classitem = (item > element.attr('data-position') ? 'left' : 'right');
			var direction = (item > element.attr('data-position') ? 'next' : 'prev');;
			
			slider.find('.item:nth-child(' + prev_item + ')').addClass(classitem);
			slider.find('.item:nth-child(' + item + ')').addClass(direction);
			setTimeout(function() {
				slider.carousel(item - 1);

				setTimeout(function () {
					sliding = false;
					motion = true;
				}, 700);
			}, 100);
			
			element.attr('data-position', sliderPosition);
		} else {
			motion = true;
			sliding = false;
		}
		slider.carousel('pause');
		
		slidePager(element, item);
		
		adjustSlider(element, slider, item);
	}
}
function slidePager(elem, item) {
	var pager = elem.find('.pager').first();
	pager.find('li').each(function() {
		$(this).removeClass('active');
	});
	pager.find('li:nth-child(' + item + ')').addClass('active');
}
function hideLeftArrow(element) {
	element.animate({
		left: ($(window).width() < 1300 ? '-70px' : '-100px'),
		display: 'hidden'
	}, 200, 'easeInOutExpo', function(){});
}
function hideRightArrow(element) {
	element.animate({
		right: ($(window).width() < 1300 ? '-70px' : '-100px'),
		display: 'show'
	}, 200, 'easeInOutExpo', function(){});
}
function showLeftArrow(element) {
	element.animate({
		left: ($(window).width() < 1300 ? 0 : 0),
		display: 'show'
	}, 200, 'easeInOutExpo', function(){});
}
function showRightArrow(element) {
	element.animate({
		right: ($(window).width() < 1300 ? 0 : 0),
		display: 'show'
	}, 200, 'easeInOutExpo', function(){});
}

function positionPager() {
	var left = 0;
	$('.sliding:first').find('.carousel-inner:first').children().each(function() {
		left = ($(window).width() >= 1300 ? ($('#benefits #b_carousel .carousel-inner .item:first').offset().left + 402) : 458); 
	});
	$('.sliding').each(function() {
		$(this).find('.pager:first').css({ left: left });
	});
        $('.pager').css('visibility','visible');
}

function adjustSlider(element, slider, item) {
	if (item > 1) {
		showLeftArrow(element.find('.prev-slide').first());
	} else {
		hideLeftArrow(element.find('.prev-slide').first());
	}
	if (item < (slider.children().children('.item').length)) {
		showRightArrow(element.find('.next-slide').first());
	} else {
		hideRightArrow(element.find('.next-slide').first());
	}
}
