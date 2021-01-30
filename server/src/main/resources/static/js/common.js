class Common {
    menu_index = 'menu_index'
    menu_org = 'menu_org'
    menu_me = 'menu_me'
    menu_course='menu_course'

    defaultUserState() {
        return {
            tip: '正在加载中...',
            user: null,
        }
    }
    CourseState(){
        return{
            course:null,
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

    /**
     * 解析uri
     * @param {Location} location
     * @return UriComponent
     */
    uri(location){
        let paths = location.pathname.split('/')
        let queries = []
        if (location.search.length > 0){
            location.search.substr(1).split('&')
                .map((v) => v.split('='))
                .forEach((v) => queries[v[0]] = v[1])
        }
        return {
            paths: paths,
            queries: queries
        }
    }
}

let common = new Common();