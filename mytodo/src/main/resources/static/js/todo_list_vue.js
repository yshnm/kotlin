var app = new Vue({
    el: '#capture_app',
    data: {
        execution_date: null
    },
    methods: {
        taskComplete: function(event) {
            send(event, 'complete')
        },
        taskDelete: function(event) {
            send(event, 'delete')
        }

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