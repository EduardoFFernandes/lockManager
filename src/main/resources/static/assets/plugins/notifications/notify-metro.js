$.notify.addStyle("metro", {
    html:
        "<div>" +
            "<div class='image' data-notify-html='image'/>" +
            "<div class='text-wrapper'>" +
                "<div class='title' data-notify-html='title'/>" +
                "<div class='text' data-notify-html='text'/>" +
            "</div>" +
        "</div>",
    classes: {
        default: {
            "color": "#fafafa !important",
            "background-color": "#ABB7B7",
            "border": "1px solid #ABB7B7"
        },
        error: {
            "color": "#fafafa !important",
            "background-color": "rgba(203, 42, 42, 0.8)",
            "border": "1px solid #cb2a2a"
        },
        success: {
            "color": "#fafafa !important",
            "background-color": "rgba(68, 137, 228, 0.8);",
            "border": "1px solid #4489e4"
        },
        info: {
            "color": "#fafafa !important",
            "background-color": "rgba(28, 168, 221, 0.8)",
            "border": "1px solid #1ca8dd"
        },
        warning: {
            "color": "#fafafa",
            "background-color": "rgba(235, 193, 66, 0.8)",
            "border": "1px solid #ebc142"
        },
        black: {
            "color": "#fafafa !important",
            "background-color": "rgba(20, 8, 45, 0.8)",
            "border": "1px solid #14082d"
        },
        white: {
            "background-color": "#e6eaed",
            "border": "1px solid #ddd"
        }
    }
});