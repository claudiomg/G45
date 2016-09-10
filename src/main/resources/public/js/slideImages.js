$('document').ready(function() {
	var images = [
	              "url(/images/ba1.jpg)",
	              "url(/images/ba2.jpg)",
	              "url(/images/ba3.jpg)",
	              "url(/images/ba4.jpg)"
	];
	var slideIndex = 0;
	carousel();
	function carousel() {
	    slideIndex++;
	    if (slideIndex > images.length) {slideIndex = 1}
	    $('.jumbotron').css("background-image", images[slideIndex-1]);
	    setTimeout(carousel, 2000); // Change image every 2 seconds
	}

})

