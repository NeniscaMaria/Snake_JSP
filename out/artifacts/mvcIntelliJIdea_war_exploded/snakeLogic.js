function init() {
    var ctx;
    var turn = [];
    //arrays for change of position
    var xV = [-1, 0, 1, 0];
    var yV = [0, -1, 0, 1];
    var queue = [];
    var snakeLength = 1;
    var board = [];
    //initialize first position of the snake
    var X = 5 + (Math.random() * (45 - 10))|0;
    var Y = 5 + (Math.random() * (30 - 10))|0;
    var direction;
    var interval = 0;
    var score = 0;
    var pressedFirst = false;

    //adding the canvas to the document
    var canvas = document.createElement('canvas');
    for (var i = 0; i < 45; i++) {
        board[i] = [];
    }
    canvas.setAttribute('width', '450');
    canvas.setAttribute('height', '300');
    ctx = canvas.getContext('2d');
    document.body.appendChild(canvas);

    //function for placing the food on the board
    function placeFood() {
        //put food at random coordinates
        var x, y;
        do {
            x = Math.random() * 45|0;
            y = Math.random() * 30|0;
        } while (board[x][y]);
        //mark the spot on the board
        board[x][y] = 1;
        //placing the food on the canvas
        ctx.fillRect(x * 10 + 1, y * 10 + 1, 10 - 2, 10 - 2);
    }
    placeFood();
    function generateObstacles(){
        var noOfObstacles = Math.random()*20+1;
        for(var i=0;i<noOfObstacles;i++) {
            var x, y;
            do {
                x = Math.random() * 45 | 0;
                y = Math.random() * 30 | 0;
            } while (board[x][y]);
            //mark the obstacle on the board
            board[x][y] = 2;
            //placing the obstacle on the canvas
            ctx.strokeRect(x * 10 + 1, y * 10 + 1, 10 -1, 10 - 1);
        }
    }
    generateObstacles();

    function clock() {

        if (turn.length) {
            dir = turn.pop();
            if ((dir % 2) !== (direction % 2)) {
                direction = dir;
            }
        }
        if (((0 <= X && 0 <= Y && X < 45 && Y < 30)) && 2 !== board[X][Y]) { //check if the position is valid
            if (1 === board[X][Y]) { //means that it caught a piece of food
                score+= 5;
                placeFood();
                snakeLength++; //the length of the snake increases
            }
            ctx.fillRect(X * 10, Y * 10, 10 - 1, 10 - 1);
            //mark the position of the snake on the matrix
            board[X][Y] = 2;
            queue.unshift([X, Y]);

            //update new position of the head of the snake
            X+= xV[direction];
            Y+= yV[direction];

            if (snakeLength < queue.length) {
                dir = queue.pop()
                board[dir[0]][dir[1]] = 0;
                ctx.clearRect(dir[0] * 10, dir[1] * 10, 10, 10);
            }

        } else if (!turn.length) {
            if (confirm("You lost! Play again? Your Score is " + score)) {
                //reinitialize the board
                ctx.clearRect(0, 0, 450, 300);
                queue = [];
                snakeLength = 1;
                board = [];
                X = 5 + (Math.random() * (45 - 10))|0;
                Y = 5 + (Math.random() * (30 - 10))|0;
                direction = Math.random() * 3 | 0;
                score = 0;
                for (i = 0; i < 45; i++) {
                    board[i] = [];
                }
                placeFood();
                generateObstacles();
                pressedFirst = false;
                window.clearInterval(interval);
            } else {
                window.clearInterval(interval);
                //end session
                //logout
                window.location = "/login.jsp";
            }
        }

    }

    //add the movement on key press
    document.onkeydown = function(e) {
        var code = e.keyCode - 37;// 0: left 1: up 2: right 3: down

        //this ensures that the snake will move when the user presses the first button:
        if(!pressedFirst) {
            pressedFirst = true;
            //set the function clock to be called once every 60 milliseconds
            interval = window.setInterval(clock, 100);
            //if the key pressed is one of the arrows, we update the direction the snake will take
            if (0 <= code && code < 4) {
                direction = code;
            }
        }
        //log the move
        addMoveToUser(code);
        if (0 <= code && code < 4 && code !== turn[0]) {
            //add direction to the beginning
            turn.unshift(code);
        } else if (-5 === code) {
            if (interval) {
                window.clearInterval(interval);
                interval = null;
            } else {
                interval = window.setInterval(clock, 60);
            }
        }
    }
}
