## 准备
我分享主题为『Material Design In Android』。因为之前毕设项目[趣闻](https://github.com/xiaweizi/QNews)中有用到「Support Design」库中的控件，对其比较熟悉。我分三部分准备：


1. `APP`准备
2. 文档准备
3. `Keynote`准备

# 一、APP准备
项目已经上传到`GitHub`上:[AndroidMD](https://github.com/xiaweizi/AndroidMD)

运行效果

![MD.gif](http://upload-images.jianshu.io/upload_images/4043475-69724c76b1f4a3d3.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

花了两个多小时做了这个`APP`，主要作为`MD`控件的功能展示，如果需要额外的特殊功能，请自行添加。

先说说完成这个`APP`的事前准备:

#### 1. 主题
主题是最近非常火的[**「终结者2:审判日」**](https://t2.16163.com/thread-3414567-1-1.html)
#### 2. 数据
数据是自己在本地写的`json`数据，很是尴尬，然后部署到**[七牛云](https://www.qiniu.com/)**上。地址是:[WeaponInfo](http://owj4ejy7m.bkt.clouddn.com/weaponInfo)，这样就可以直接访问地址获取`json`数据。

#### 3. 语言
用的语言是之前学的**`Kotlin`**。[Kotlin学习笔记](http://www.jianshu.com/p/f132e368b88d)
#### 4. 风格
整体的风格就是我这次分享的主题 `Material Desing`风格。

# 二、整体内容结构的准备

在做`PPT`之前，先把结构搭好，并且把`PPT`的内容先准备好，到时候直接就可以复制到`PPT`中。

整体结构:

> 1. 什么是 `Material Design`
> 2. `Material Desing`的特点
> 3. 从四个特点结合`Android`的应用剖析
> 4. 在我的公司「口袋」项目中的应用

当然内容需要看官方的文档和其他资料加上总结才能完成，所以在此感谢一下文章的帮助：

> [Material Design 学习笔记](http://www.uisdc.com/comprehensive-material-design-note)
> [Material Design 官网介绍文档](https://material.io/guidelines/)
> [Material Design 中文介绍](http://md.maxoxo.design/#)
> [Material Design in Android Developer](https://developer.android.com/training/material/index.html)

# 三、`PPT`的准备

有了之前内容的编写，做`PPT`就方便一点。但是因为刚买的`MAC`，但又不想再装`WPS`套餐，于是用的是自带的`keynote`，所以使用上会有点生疏。不过，整个`PPT`制作下来对其使用也熟练了起来。

如果需要的话，可以加个QQ发给你。

### 1、封面

![封面](http://upload-images.jianshu.io/upload_images/4043475-e5c6b96043664d11.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

> 进入`MD`官网首页就是这张图片。

### 2、介绍

![介绍.gif](http://upload-images.jianshu.io/upload_images/4043475-82acd36ec2a5e1d5.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

> 从`MD`上截取的动画作为入口，大概讲解一下`MD`的基础概念和特点。

### 3、特点

![特点.gif](http://upload-images.jianshu.io/upload_images/4043475-9530c68384a306db.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

> 这里抽取了四个点:`Material`、`Elevation`、`Color`和`Animation`进行分析。

### 4、风格背景

> 文字采用圆角+阴影进行包裹，至于高度和圆角效果因为时间紧迫，没有按照严格规范进行设置，如果对这方面有要求可以参考官网详细的规范要求。

![image.png](http://upload-images.jianshu.io/upload_images/4043475-293024e20bdcfa9d.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 5、动画效果

> 说起动画，为了能够模仿`MD`的交互，也是现学现卖了一把。

![交互.gif](http://upload-images.jianshu.io/upload_images/4043475-5fda106fc9875d3e.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

其实就是背景的放大效果，再加上文字的位移效果。


----------

----------

我--------------是--------------分--------------割--------------线

----------

----------


# **Material Design in Android**

接下来开始分享这次分享的主要内容,因为`MD`的介绍和规范在官网上都有非常详细全面的介绍，所以我就不赘述了，建议自己先看一遍官方网站的介绍，这样你对`MD`的理解会更加深入一些。那我把链接再列出一下：

> [Material Design`官网介绍文档](https://material.io/guidelines/)
> [Material Design 中文介绍](http://md.maxoxo.design/#)

当你把官网的内容大致浏览一遍，相信也对`MD`有个初步的了解，当然要想全部弄懂的话，还得需要消化一阵子，毕竟`MD`的设计规范细致入微。越读越能感受到它的妙处，假如你能严格按照它的规范进行开发项目，哪怕你不是专业的UI设计师，相信你的产品一定不会难看的。

那接下来就主要介绍一下`Material Desing`在`Android`中应用。。

跟随着15年`Android 5.0`的问世，谷歌设计师们还给我们带来的一系列的具有`Material Design`风格控件。这些控件被统一放置在`support design`库中，以供开发中使用。使用这些库的前提是`API>=21`，当然如果你想在 5.0 一下的设备这些控件的话，需要添加`appcompat`包进行向下兼容。

![image.png](http://upload-images.jianshu.io/upload_images/4043475-eff26b8e6f76e5d3.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

我的`design`版本是`26.1.0`,上图大概就是`design`提供的`API`，这里我只做简单的使用介绍，如果想了解其原理的话，可以看一下官方的介绍。

> 这么多我该从何说起呢？我想了下，就按照我做这个小项目，需要的控件顺序说起吧，这样也相当于大家跟我一起做出一个具有`Material Design`风格的`APP`了。

### 1、主题
一个项目的开始，你得先确定这个项目的主题颜色是什么？你可以使用谷歌给你提供的`Material Theme`:

- `@android:style/Theme.Material`（深色版本）
- `@android:style/Theme.Material.Light`（浅色版本）
- `@android:style/Theme.Material.Light.DarkActionBar`

当然，也可以使用自定义的主题，先看一下最为常见的图片:

![image.png](http://upload-images.jianshu.io/upload_images/4043475-8325db1456ddf0d2.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

可以通过定制不同的类别的主题颜色，来达到预期的主题效果。

- `colorPrimary` 项目主颜色，一般是`Titlebar`的背景颜色
- `colorPrimaryDark` 比主颜色深一点颜色，一般是状态栏颜色
- `textColorPrimary` 文字的主颜色
- `windowBackground` 窗口背景颜色
- `navigationBarColor` 导航栏颜色

通过在`styles`中配置颜色来定制您的主题，并在`AndroidManifest`中应用。



### 2、BottomNavigationView

主题构建好了，下面就是主要内容架构，我大致分为四个模块:武器简介、人物简介、配件简介和空投简介。那么底下就需要一个`tab`进行切换，`BottomNavigationView`便开始登场。从名字就可以看出 「底部导航`view`」，主要的作用在于给每个模块一个导航定位的功能，相信大家在众多`App`中都能发下底部导航`view`的身影。

先看一下效果:

![bottomNavigationView.gif](http://upload-images.jianshu.io/upload_images/4043475-6e02ff6f1cafcc39.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


1. 在`menu/`下创建菜单文件:

        <menu xmlns:android="http://schemas.android.com/apk/res/android">
            <group android:checkableBehavior="single">
                <item
                    android:id="@+id/bottom_weapon_inc"
                    android:icon="@drawable/about"
                    android:title="@string/weapon_inc" />
                <!--  省略部分代码     -->
            </group>
        </menu>

2. `XML`中进行引用

        <android.support.design.widget.BottomNavigationView
            android:id="@+id/navigation"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:itemBackground="@color/colorPrimary"
            app:itemIconTint="@android:color/white"
            app:itemTextColor="@android:color/white"
            app:menu="@menu/bottom_menu"/>

3. 代码中设置点击事件

        navigation!!.setOnNavigationItemSelectedListener {}
    

### 3、DrawerLayout、NavigationView

和`BottomNavigationView`相对应的，不得不介绍一下`NavigationView`，这两者都是导航`View`，后者一般需要配合`DrawerLayout`实现侧滑菜单效果。

请看效果：

![DrawerLayout.gif](http://upload-images.jianshu.io/upload_images/4043475-c386ac73a2c4877e.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

在`XML`直接引用

    <android.support.v4.widget.DrawerLayout
            android:id="@+id/dl_main"
            android:layout_width="match_parent"
            android:layout_weight="1"
            android:layout_height="0dp">
            <!-- 主内容 -->
            <FrameLayout
                android:fitsSystemWindows="true"
                android:id="@+id/fl_content"
                android:layout_width="match_parent"
                android:layout_height="match_parent">
    
            </FrameLayout>
            <!-- 侧滑菜单内容 -->
            <android.support.design.widget.NavigationView
                android:id="@+id/nv_left"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:layout_gravity="start"
                app:headerLayout="@layout/nav_header"
                app:menu="@menu/nav_menu"/>
        </android.support.v4.widget.DrawerLayout>

通过配置`layout_gravity`的属性来设置侧滑的方向：`start`：从左侧划出，`end`从右侧划出。

    headerLayout: 设置其头布局
    menu: 设置菜单布局

详细使用请看我之前写的一篇博客：[高大上的`DrawerLayout`](http://www.jianshu.com/p/ce8a7a20c03c)

### 4、Toolbar

整体的架构搭建好了，剩下就是开始每个模块的内容了，内容当然少不了标题，那么就开始介绍一下`Toolbar`。

`Toolbar`作为早期`Android`中`ActionBar`的替代品，定制性和操作性挺高了不少。使用的时候需要设置`NoActionBar`的主题。

    <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar"></style>

### 5、RecyclerView+SwipeRefreshLayout

项目中列表肯定是少不了的，那么这就不得不提`RecyclerView`了，强大之处不用多说，感兴趣的话看一下我之前写的博客，对其使用有个简单的介绍：[简单粗暴`RecyclerView`](https://www.jianshu.com/p/60819de9eb42)

那如果想实现侧滑删除和长按拖拽的功能怎么办呢？`RecyclerView`原生就支持这些，只需要继承`ItemTouchHelper.Callback`的类，并实现它几个抽象方法即可。

1. 创建实现`ItemTouchHelper.Callback`的类

        internal inner class ItemTouchHelperCallback : ItemTouchHelper.Callback() {
            override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
                val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
                val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
                return makeMovementFlags(dragFlags, swipeFlags)
            }
    
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                myAdapter!!.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
                return false
            }
    
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                myAdapter!!.onItemDismiss(viewHolder.adapterPosition)
            }
        }

2. 和`RecyclerView`建立连接

        val mItemTouchHelper = ItemTouchHelper(ItemTouchHelperCallback())
        mItemTouchHelper.attachToRecyclerView(mRecyclerView)
        
实现效果如下：

![RecyclerView.gif](http://upload-images.jianshu.io/upload_images/4043475-276e4cc24cd6a09b.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 6、CardView

列表结构写好了，里面内容得优化吧，`CardView`自带圆角和阴影效果，让每个`Item`看起来就非常的自然，正如其名像卡片一样，也符合了`Material Design`特点。

作为`ViewGroup`包裹子`View`实现圆角和阴影的效果：

    <android.support.v7.widget.CardView
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="2dp"
        app:cardCornerRadius="5dp"
        app:cardElevation="5dp">
    </android.support.v7.widget.CardView>

主要由两个属性控制：

- `cardCornerRadius`：圆角半径
- `cardElevation`：高度(直接影响阴影的大小)


### 7、CoordinatorLayout+AppBarLayout+Toolbar

列表写好了，接下来就是滑动的交互，`CoordinatorLayout`：作为根`View`或者是一个活多个子`View`特定的容器，用于协调子`View`之间滑动的交互，可以说`CoordinatorLayout`是整个`Design`库中最核心的控件。

`AppBarLayout`其实就是`LinearLayout`,通过`layout_scrollFlags`来控制滑动的效果。前提是滑动`view`必须实现`NestedScrollingChild`的接口，且需要配置`behavior`，最基本的使用就是：

    <android.support.design.widget.CoordinatorLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
        
        <android.support.design.widget.AppBarLayout
            android:id="@+id/appbar"
            android:layout_width="match_parent"
            android:layout_height="wrap_content">
            <android.support.v7.widget.Toolbar
                android:id="@+id/toolbar"
                android:layout_width="match_parent"
                android:layout_height="?attr/actionBarSize"
                android:background="?attr/colorPrimary"
                android:fitsSystemWindows="true"
                android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar"
                app:layout_scrollFlags="scroll|enterAlwaysCollapsed"
                app:popupTheme="@style/Theme.AppCompat.Light" />
        </android.support.design.widget.AppBarLayout>
    
        <android.support.v4.widget.SwipeRefreshLayout
            android:id="@+id/refresh_layout"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            app:layout_behavior="@string/appbar_scrolling_view_behavior">
            <android.support.v7.widget.RecyclerView
                android:id="@+id/recyclerView"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:foregroundGravity="center" />
        </android.support.v4.widget.SwipeRefreshLayout>
    
    </android.support.design.widget.CoordinatorLayout>

有两个重点：

- 滑动的`view`必须实现`NestedScrollingChild`接口。比如`RecyclerView`、`NestedScrollView`.
- 必须配置`behavior`。`app:layout_behavior="@string/appbar_scrolling_view_behavior"`

重点看一下`layout_scrollFlags`有哪些属性，为了方便理解，将可以滑动的`view`简称为`ScrollView`,设置了`layout_scrollFlags`称为`DependentView`，两者滑动事件的传递通过配置不同的`layout_scrollFlags`达到不同的交互效果。

#### 1. scroll
子`view`必须设置该属性其他的属性的才会生效，这个是最基本的属性。

#### 2. scroll|enterAlways

`DependentView`事件处理的优先级要大于`ScrollView`，当手指在屏幕上滑动，滑动事件便首先交给`DependentView`处理，当`DependentView`滑动结束才会将事件交给`ScorllView`。也就是下面的效果:

![enterAlways.gif](http://upload-images.jianshu.io/upload_images/4043475-df42e9245f9747ed.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### 3. scroll|enterAlwaysCollapsed

当`ScrollView`向下滑动时，`DependentView`先折叠到最小高度(这里是0),然后将事件交给`ScrollView`，当`ScrollView`滑动结束，`DependentView`才继续滑动事件，直至展开，如下图所示：

![enterAlwaysCollapsed.gif](http://upload-images.jianshu.io/upload_images/4043475-2941d0adaa3ada9c.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### 4. scroll|enterAlwaysCollapsed|enterAlways

这边就展示一下折叠的效果，我们先设置最小的高度

        <android.support.v7.widget.Toolbar
            android:layout_width="match_parent"
            android:layout_height="100dp"
            android:minHeight="50dp"
            app:layout_scrollFlags="scroll|enterAlwaysCollapsed|enterAlways" />

展示一下效果：

![see.gif](http://upload-images.jianshu.io/upload_images/4043475-24e0d5bd7a84b488.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### 5. scroll|exitUntilCollapsed

这个搭配重点在于上拉的时候，`DependentView`会先折叠到最小高度，然后事件全部交给`ScrollView`。那下拉的时候就是当`ScrollView`滑动结束，才开始`DependentView`的滑动事件。

![exitUntilCollapsed.gif](http://upload-images.jianshu.io/upload_images/4043475-a125be21d3383259.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

#### 6. scroll|enterAlways|snap

这个`snap`就是在上面的基础上多了一个回弹的效果,一般需要配合上其他属性使用才会有效果。当`DependentView`正在滑动，此时手指离开屏幕时，`DependentView`会自动移动到离自己较近的终点或者始点。效果如下:

![snap.gif](http://upload-images.jianshu.io/upload_images/4043475-5f1f30e9b70ca8ad.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


上面的属性完全可以像第四种情况叠加使用，至于效果自己尝试了了才能感觉到它的奥妙之处。

### 8、转场动画

交互有了，现在看是添加点击跳转效果了。咱们之前跳转动画都是在`startActivity`之后调用`overridePendingTransition`方法，传入进入和退出的动画实现跳转动画。`Android 5.0`提供了强大的转场动画，给每个`item`赋予了生命，我们可以把`Activity`的跳转比作一次搬迁大运动，而布局中的每个`view`都参与到了这次搬迁运动中。

使用时，需在`setContentView()`之前加上

    window.requestFeature(Window.FEATURE_CONTENT_TRANSITIONS)
    
跳转时候这样写:

    startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle())

跳转的界面设置转场动画或者出场动画：

        window.enterTransition = Explode()
        window.exitTransition = Slide()
        
谷歌给我提供了三种转场方式:

- Explode() 突然的变形
- Slide() 自然的滑动
- Fade() 渐变

为了看出效果我设置了2s的延迟：

![Transition.gif](http://upload-images.jianshu.io/upload_images/4043475-fe29a3732dfc83f6.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

### 9、Toast、SnackBar和AlertDialog

基本的界面写完了，剩下的就是一些逻辑上的操作啦，比如「提示」。那么`Android`提示分为三种：

- 友好的`Toast`(比如网络失败)
- 拥有附加行为的提示`SnackBar`(比如误删信息回撤)
- 强制让用户做出选择的`AlertDialg`(比如未登录)

那么这三种的效果是什么呢？

![TSA.gif](http://upload-images.jianshu.io/upload_images/4043475-18678d48084a5701.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

大概先讲这些，有时间再进行后续补充。

# `Material Design`在「口袋」中的应用

其实在咱们的「口袋贵金属」项目中也到找到很多`MD`的元素。

首先是点击的水波纹效果：

![ripple.gif](http://upload-images.jianshu.io/upload_images/4043475-a19996587c89e708.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

其次是交易圈的滑动交互:

![circle.gif](http://upload-images.jianshu.io/upload_images/4043475-9ecae2ddb8be50ef.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

还有就是本人在「口袋」接手的第二个需求，「个人中心」。看一下效果：

![personal.gif](http://upload-images.jianshu.io/upload_images/4043475-4431f1f722e17eb2.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


> 严格按照`Material Design`风格进行开发，相信一定能开发出非常漂亮的`APP`！！
