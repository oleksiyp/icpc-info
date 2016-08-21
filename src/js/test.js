var page = require('webpage').create();

page.viewportSize = { width: 1280, height: 1024 };
page.zoomFactor = 1;

page.open('src/js/test.html', function (status) {
    if (status !== 'success') {
        console.log('Unable to load the address!');
        phantom.exit(1);
        return
    }

    window.setTimeout(function () {
        page.render("result.png");
        phantom.exit();
    }, 200);
});
