
function confirmDeletion(){
    if (confirm('Delete Recipe?')){
        return true;
    }else {
        event.stopPropagation(); event.preventDefault();
    }
}