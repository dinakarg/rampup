$(document).ready(function(){
	$('#myCarousel').carousel({
	  interval: 2000
	})
	
	$('#openSections').on('click', function(e){
		$('.searchSectionsBlock').show();
		e.stopPropagation();
		
	});
	
	$(".searchSectionsBlock").click(function(e){
		e.stopPropagation();
	});

	$(document).click(function(){
	$(".searchSectionsBlock").hide();

	});
	$('.listmenu ul li, .sectionLink, .sectionLink1').click(function(){
		$(".searchSectionsBlock").hide();

	});

	$('#search_boxShow').on('click', function(e){
		$('.search_box').show();
		e.stopPropagation();	
	});
	$(".search_box").click(function(e){
		e.stopPropagation();
	});
	$(document).click(function(){
		$(".search_box").hide();

	});
	
	
	});
	
	
	
	// Scripts for sliders(bx-slider)
$(document).ready(function(){
	// Opinon slide show script
    $('.Opinion').bxSlider({
        infiniteLoop: false,
        hideControlOnEnd: true,
        pager: false,
        slideWidth: 291,
        maxSlides: 4,
        minSlides:1
    });
    // Events slide show script
    $('.Events').bxSlider({
        infiniteLoop: false,
        hideControlOnEnd: true,
        pager: false,
        slideWidth: 260,
        maxSlides: 5,
        minSlides:1
      });		  

	// OurVoices
    $('.OurVoices').bxSlider({
			infiniteLoop: false,
			hideControlOnEnd: true,
			pager: false,			
			slideWidth: 390,
			maxSlides: 3,
			minSlides:1
		});
    // Selected Content
    $('.selectedcontent').bxSlider({
			infiniteLoop: false,
			hideControlOnEnd: true,
			pager: false,			
			slideWidth: 350,
			maxSlides: 4,
			minSlides:1
		});
    // Selected Content
    $('.Slideshows').bxSlider({
			infiniteLoop: false,
			hideControlOnEnd: true,
			pager: false,			
			slideWidth: 350,
			maxSlides: 4,
			minSlides:1
		});	
		// classifieds
	$('.classifieds').bxSlider({
			infiniteLoop: false,
			hideControlOnEnd: true,
			pager: false,			
			slideWidth: 390,
			maxSlides: 3,
			minSlides:1
	});
	$('.lastcomments').bxSlider({
			infiniteLoop: false,
			hideControlOnEnd: true,
			pager: false,			
			slideWidth: 300,
			maxSlides: 4,
			minSlides:1
	});  
});
	
	
	  
  