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

    /**
     * 判断当前是否处于登录状态
     * @param state
     * @returns {boolean}
     */
    isLogin(state) {
        return state.user !== null
    }

    /**
     * 判断一个请求是否status=200且code=200
     * @param res
     * @return boolean
     */
    resOk(res) {
        return res !== net.net_fail && res.code === 200
    }
}

let common = new Common();