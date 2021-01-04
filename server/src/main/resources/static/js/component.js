Vue.component('app-compact',{
    props: ['c'],
    name: 'app-compact',
    template: `
<div>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/">企业在线学习平台</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarTogglerDemo02" aria-controls="navbarTogglerDemo02" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    
    <div class="collapse navbar-collapse" id="navbarTogglerDemo02">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <li class="nav-item" :class="c.menu === c.common.menu_index ? 'active': ''">
                <a class="nav-link" href="/">主页</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/org">组织</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/me">个人信息</a>
            </li>
        </ul>
        <div class="form-inline my-2 my-lg-0">
            <label>
                <input class="form-control mr-sm-2" type="search" name="name" placeholder="搜索课程">
            </label>
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">搜索</button>
        </div>
    </div>
    </nav>
    <slot></slot>
</div>`

})
