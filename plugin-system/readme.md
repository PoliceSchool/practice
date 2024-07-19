1. 一个插件系统Demo，主要用来练习Java的类加载过程
2. 主要分为三部分：plugin-api、plugin-impl、plugin-manager，分别是插件接口定义层、插件接口实现层、插件管理层
3. 流程是：
    * 在plugin-api层定义一个插件接口，然后将其install到maven仓库中；
    * 在plugin-impl层引入plugin-api依赖，然后实现插件接口，，然后将其install到maven仓库中；
    * 在plugin-manager层引入plugin-api依赖，然后使用URLClassLoader加载plugin-impl的jar包中的插件接口实现类，然后调用其方法
4. 这样就可以实现一个可插拔插件系统系统，在plugin-manager层只需要依赖plugin-api，而不用关心具体实现是哪个依赖，它自己会根据参数找到对应的jar包中的对应的类


