package com.example.demo.view

import tornadofx.*

class MainView : View("Hello TornadoFX") {
    val topView:TopView by inject()
    val centerView = find(CenterView::class)
    override val root = borderpane {
        //creating a lazy reference to topView
        top = topView.root

        center = centerView.root

        bottom<BottomView>()

        left<LeftView>()

        right<RightView>()
    }
}
