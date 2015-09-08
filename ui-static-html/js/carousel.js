$(document).ready(function(){
	$('#myCarousel').carousel({
	  interval: 2000
	})

	

	});


$('#gallery-1').royalSlider({
        fullscreen: {
          enabled: true,
          nativeFS: true
        },
        controlNavigation: 'thumbnails',
        autoScaleSlider: true, 
        autoScaleSliderWidth: 650,     
        autoScaleSliderHeight: 434,
        loop: false,
        imageScaleMode: 'fill',
        navigateByClick: true,
        numImagesToPreload:2,
        arrowsNav:true,
        arrowsNavAutoHide: true,
        arrowsNavHideOnTouch: true,
        keyboardNavEnabled: true,
        fadeinLoadedSlide: true,
        globalCaption: true,
        globalCaptionInside: false,
        thumbs: {
            appendSpan: true,
            firstMargin: true,
            paddingBottom: 4,
    	    spacing:6,
    	    arrowsAutoHide: false
        }
    });