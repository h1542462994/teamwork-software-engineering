class Common {
    menu_index = 'menu_index'
    menu_org = 'menu_org'
    menu_me = 'menu_me'

    defaultUserState() {
        return {
            tip: '正在加载中...',
            user: null,
        }
    }
}

let common = new Common();