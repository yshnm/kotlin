var app = new Vue({
    el: '#capture_app',
    data: {
        // 一覧表示されているタスクのリスト
        taskList: [],
        // key:サブタスクのカウント value:ネクストサブタスクのカウント
        insertSubtaskCount: {0 : 0},
        // 登録するタスクデータのオブジェクト
        inputTask: null,
        // 登録するサブタスクリストんぼオブジェクト
        inputSubTaskList: [],
        // 一覧表示対象となるタスクの完了フラグ ("0"：未完了タスクの一覧 "1":完了済タスクの一覧)
        displayCompletedFlg: "0"
    },
    methods: {
        taskComplete: function(event) {
            /*
              タスク完了処理
            */
            var taskObj = new Object();

            taskObj.id = event.target.id;
            taskObj.taskKind = event.target.dataset.kind;

            var newTaskList = this.post(event, JSON.stringify(taskObj), 'complete');

            this.taskList = newTaskList;
        },
        taskDelete: function(event) {
            /*
              タスク削除処理
            */
            var taskObj = new Object();

            taskObj.id = event.target.id;
            taskObj.taskKind = event.target.dataset.kind;
            taskObj.completeFlg = this.displayCompletedFlg;

            var newTaskList = this.post(event, JSON.stringify(taskObj), 'delete');

            this.taskList = newTaskList;
        },
        switchInsertArea: function(event) {
            /*
              登録エリア 表示/非表示切り替え
            */
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
               this.inputSubTaskList = [];

               // 登録欄を非表示
               insertAreaNode.style.display = 'none';
               var a = document.querySelectorAll('#insert-area .input-element');

               // ボタンの表示値を「add...task」に変更
               event.target.innerText = 'add...task'
            }

        }, addSubtask: function(event) {
            /*
              サブタスク入力欄追加処理
            */
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
            /*
               登録処理
            */
            var taskObj = new Object();

            taskObj.title = this.inputTask.title == undefined ? '': this.inputTask.title;
            taskObj.detail = this.inputTask.detail == undefined ? '': this.inputTask.detail;
            taskObj.executionDate = this.inputTask.execution_date == undefined ? '': this.inputTask.execution_date;

            var subTaskList = this.inputSubTaskList.map(element => {

                var subTaskObj = new Object();
                subTaskObj.title = element.title == undefined ? '': element.title;
                subTaskObj.detail = element.detail == undefined ? '': element.detail;
                subTaskObj.executionDate = element.execution_date == undefined ? '': element.execution_date;

                return subTaskObj;
            })

            taskObj.subTaskList = subTaskList;

            var newTaskList = this.post(event, JSON.stringify(taskObj), 'insert');

            this.taskList = newTaskList;

            // 登録・一覧再描画後「chancel」ボタンをクリックし、insertエリアを閉じる
            document.querySelector('#add-task-btn').click();
        }, post: function(event, obj, name) {
            /*
              コントローラとのAJAXを利用した同期通信
            */
            var xmlHttpRequest = new XMLHttpRequest();

            //【手順4】リクエストメソッドと読み込むファイルのパスを指定する。
            xmlHttpRequest.open('POST', event.target.baseURI + name, false);
            xmlHttpRequest.setRequestHeader('Content-Type', 'application/json');
            xmlHttpRequest.setRequestHeader("Accept","application/json");

            xmlHttpRequest.send(obj);

            if(xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
                return JSON.parse(xmlHttpRequest.response);
           }
        }, switchCompleted(event) {
　　        /*
              一覧に表示するタスクリストの切り替え（未完了 or 完了済み）
            */
            var taskObj = new Object();

            taskObj.completeFlg = this.displayCompletedFlg;

            var newTaskList = this.post(event, JSON.stringify(taskObj), 'switch');

            this.taskList = newTaskList;
        }

    },
    created: function() {
        // 初期表示時：取得したタスクリストを配列に変換
        this.taskList = JSON.parse(document.querySelector('#in-completed-task-list').value);
        this.inputTask = [];
    }
})
