var tabs = document.getElementsByName('tab');
var contents = document.getElementsByName('divcontent');

(function changeTab(tab) {
    for (var i = 0, len = tabs.length; i < len; i++) {
        tabs[i].onmouseover = showTab;
    }
})();

function showTab() {
    for (var i = 0, len = tabs.length; i < len; i++) {
        if (tabs[i] === this) {
            tabs[i].className = 'selected';
            contents[i].className = 'show';
        } else {
            tabs[i].className = '';
            contents[i].className = 'tab-content';
        }
    }
}