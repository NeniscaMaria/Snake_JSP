function addMoveToUser(move){
    $.post("SnakeController",{ move:move});
}