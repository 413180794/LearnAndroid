# 日常工作总结用
## 滑动冲突 
 slideFragment
  ### FrameLayout 源码
  ### 为什么ScrollView 套 ListView会有滑动冲突？
      下面需要整理一下
     答案或许在scrollview的onInterceptTouchEvent方法中，因为scrollView会充当mTouchTarget，
      所以，down事件，或者move事件都会传递到onInterceptTouchEvent方法中，而这个方法中，会在move事件的时候，将
        自己的intercept 改成true，之后，会给ListView补一个cancel事件，这样listview就不会再接收到move事件了。
        导致listview套scrollview的时候，listview是无法滑动的。

      所以该怎么解决呢，我们需要在listView的onInterceptTouchEvent方法中，将move事件的时候，调用requestDisallowInterceptTouchEvent(true)，
      这样， 就不会走scrollview onInterceptTouchEvent方法中的逻辑了，也就不会给listview补一个cancel事件了。