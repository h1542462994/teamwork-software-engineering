Vue.component('app-compact',{
    props: ['c', 'state'],
    name: 'app-compact',
    template: `
<div>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark my-padding">
    <a class="navbar-brand" href="/">企业在线学习平台</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    
    <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <li class="nav-item" :class="c.menu === c.common.menu_index ? 'active' : ''">
                <a class="nav-link" href="/">主页</a>
            </li>
            <li class="nav-item" :class="c.menu === c.common.menu_org ? 'active' : ''">
                <a class="nav-link" href="/org">组织</a>
            </li>
            <li class="nav-item" :class="c.menu === c.common.menu_me ? 'active' : ''">
                <a class="nav-link" href="/me">个人信息</a>
            </li>
            <li class="nav-item" :class="c.menu === c.common.menu_course ? 'active' : ''">
                <a class="nav-link" href="/courseManage">课程管理</a>
            </li>
        </ul>
        <div class="form-inline my-2 my-lg-0">
            <a class="btn btn-primary btn-block" v-if="state.user === null" href="/login">点击登录</a>
            <div v-else>
                <a class="card-link" href="/me">{{ state.user.name }}</a>
            </div>
        </div>
    </div>
    </nav>
    <div v-if="state.user === null">你当前还没有登录</div>
    <slot v-else></slot>
</div>`

})
