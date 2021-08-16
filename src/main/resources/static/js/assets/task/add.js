

$(".team").change(function() {
     var id = $(this).attr('id');
      var multi = $('.person');
    if(this.checked) {
     $.each(multi, function (index, item) {
         if($(this).data('parent') == id) {
           $(this).prop('checked', true);
         }
     });
    }else{
    $.each(multi, function (index, item) {
             if($(this).data('parent') == id) {
               $(this).prop('checked', false);
             }
         });

    }
});