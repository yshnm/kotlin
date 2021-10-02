/*
  共通処理
*/

// 本日の日付
var toDate = new Date()

// 日付フォーマット変換
function formatDate(dt) {
    var y = dt.getFullYear();
    var m = ('00' + (dt.getMonth() + 1)).slice(-2);
    var d = ('00' + dt.getDate()).slice(-2);
    var result = y + '/' + m + '/' + d;

    return result;
}

// ヘッダの現在日付表示
window.onload = function() {
    var headerDate = document.querySelector('#headerDate');
    headerDate.textContent = formatDate(toDate);
}


