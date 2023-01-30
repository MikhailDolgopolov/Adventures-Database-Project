function LoadTrips(){
    ShowOrHide('add-trip-form');
    submitFilter();
}
function LoadTrip(){
    ShowOrHide('add-persons-form');
}
function HideOneShowAnother(id, other) {
    let vis1 = document.getElementById(id).style.display;
    if(vis1!=='none'){
        document.getElementById(id).style.display='none'
        document.getElementById(other).style.display=vis1;
    }else{
        document.getElementById(id).style.display=document.getElementById(other).style.display;
        document.getElementById(other).style.display='none';
    }
}

function ShowOrHide(id, was='block'){
    let vis = document.getElementById(id).style.display;
    if(vis!=='none') {
        document.getElementById(id).style.display = 'none'
    }else {
        document.getElementById(id).style.display = was;
    }
}
function submitFilter()
{
    $.ajax({
        type: 'post',
        data: {'filter':$('#filter').val()},
        url: '/trips/setFilter/',

        success: function(data){
            $('.filtered-trips').html(data);
        },
    })
}
function confirmDeletion(id){
    if(confirm('Вы собираетесь удалить данные. Продолжить?')){
        document.getElementById(id).submit();
    }
}