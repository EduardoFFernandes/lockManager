var joditButtons = [
    'bold',
    'italic',
    'underline',
    'strikethrough',
    '|',
    'align',
    'outdent',
    'indent',
    '|',
    'ul',
    'ol',
    '|',
    'font',
    'fontsize',
    'brush',
    'paragraph',
    '|',
    'image',
    'table',
    'link',
    '|',
    'hr',
    'copyformat',
    'eraser',
    '\n',
    'undo',
    'redo',
    '|',
    'selectall',
    'fullsize',
    'print'
];

if(window.JODIT_HTML && JODIT_HTML == true){
    joditButtons.unshift('|')
    joditButtons.unshift('source')
}

function joditLanguage(){
    var especialLanguages = ['pt', 'zh', 'cs']
    var language;
    if(especialLanguages.includes(SYSTEM_LANGUAGE)){
        language = SYSTEM_LOCALE.toLowerCase().replace('-', '_')
    } else {
        language = SYSTEM_LANGUAGE
    }
    return language
}

var joditDefaultConfig = {
    language: joditLanguage(),
    height: 300,
    buttons: joditButtons,
    buttonsMD: joditButtons,
    buttonsXS: joditButtons,
    toolbarButtonSize: 'large',
    indentMargin: 20,
    allowResizeX: false,
    allowResizeY: true,
    defaultMode: Jodit.MODE_WYSIWYG,
}

