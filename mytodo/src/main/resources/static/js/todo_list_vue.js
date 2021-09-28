var app = new Vue({
    el: '#capture_app',
    data: {
        taskList: null,
        // key:サブタスクのカウント value:ネクストサブタスクのカウント
        insertSubtaskCount: {0 : 0},
        inputTask: null,
        inputSubTaskList: []
    },
    methods: {
        taskComplete: function(event) {
            // タスク完了処理 サーバ側と非同期通信を行う
            send(event, 'complete')
        },
        switchInsertArea: function(event) {

            // 登録エリア 表示/非表示切り替え
            var insertAreaNode = document.querySelector('#insert-area');

            if(insertAreaNode.style.display == 'none'
                || insertAreaNode.style.display == '') {
                // add...taskボタンクリック時

                // 登録オブジェクト生成
                this.inputTask = new Object();

                // 登録欄を表示
                insertAreaNode.style.display = 'block';

                // ボタンの表示値を「cancel」に変更
                event.target.innerText = 'cancel'
            } else {
               // 登録オブジェクト削除
               this.inputTask = new Object();

               // 登録欄を非表示
               insertAreaNode.style.display = 'none';

               // 入力値を削除
               document.querySelectorAll('#insert-area .input-element')
                   .forEach(element => element.value = '');

               // ボタンの表示値を「add...task」に変更
               event.target.innerText = 'add...task'
            }

        }, addSubtask: function(event) {

            var subtaskAreaNode = document.querySelector('#subtask-area');

            // キー(サブタスクのカウント)の最大値
            var subTaskCount = Object.keys(this.insertSubtaskCount)
              .reduce((val, nextVal) => Math.max(val, nextVal));
            subTaskCount++

            // カウントオブジェクトに新しいサブタスクカウントの要素を追加
            this.insertSubtaskCount[subTaskCount] = 0;

            // サブタスクオブジェクト生成
            var subTaskObj = new Object();

            // カウントを代入
            subTaskObj.count = subTaskCount;

            // サブタスクオブジェクトを登録リストに代入
            this.inputSubTaskList.push(subTaskObj);

        }, insert: function(event) {
            // 登録処理

            var taskObj = new Object();

            taskObj.title = this.inputTask.title;
            taskObj.detail = this.inputTask.detail;
            taskObj.executionDate = this.inputTask.execution_date;

            var subTaskList = this.inputSubTaskList.map(element => {

                var subTaskObj = new Object();
                subTaskObj.title = element.title;
                subTaskObj.detail = element.detail;
                subTaskObj.executionDate = element.execution_date;

                return subTaskObj;
            })

            taskObj.subTaskList = subTaskList;

            var newTaskList = insertPost(event, JSON.stringify(taskObj));

            this.taskList = newTaskList;

            // 登録・一覧再描画後「chancel」ボタンをクリックし、insertエリアを閉じる
            document.querySelector('#add-task-btn').click();
        }
        /*
          TODO タスク削除処理実装予定
        taskDelete: function(event) {
            send(event, 'delete')
        }
        */

    },
    created: function() {
        // 初期表示時：取得したタスクリストを配列に変換
        this.taskList = JSON.parse(document.querySelector('#inCompletedTaskList').value);
        this.inputTask = new Object();
    }
})


/*
  TODO 説明
*/
function send(event, operation) {

    var id = event.target.parentNode.id;
    var taskKind = event.target.parentNode.dataset.kind;

    var xmlHttpRequest = new XMLHttpRequest();

        xmlHttpRequest.onreadystatechange = function() {

            //レスポンスの受信が正常に完了したとき
            if(this.readyState == 4 && this.status == 200) {

                var removeElem = event.target.parentNode;
                // 配下の要素を全て削除
                while (removeElem.firstChild) {
                    removeElem.removeChild(removeElem.firstChild);
                }
            }
        }

    //【手順3】レスポンスの形式を指定する。
    xmlHttpRequest.responseType = 'json';

    //【手順4】リクエストメソッドと読み込むファイルのパスを指定する。
    xmlHttpRequest.open('GET', event.target.baseURI + operation + '/'+ id + '/' +  taskKind);
    xmlHttpRequest.setRequestHeader('content-type', 'application/x-www-form-urlencoded;charset=UTF-8');
    xmlHttpRequest.send();
}


function insertPost(event, obj) {

    var xmlHttpRequest = new XMLHttpRequest();

    //【手順4】リクエストメソッドと読み込むファイルのパスを指定する。
    xmlHttpRequest.open('POST', event.target.baseURI + 'insert', false);
    xmlHttpRequest.setRequestHeader('Content-Type', 'application/json');
    xmlHttpRequest.setRequestHeader("Accept","application/json");

    xmlHttpRequest.send(obj);

    if(xmlHttpRequest.status == 200) {
         return JSON.parse(xmlHttpRequest.response);
    }
}