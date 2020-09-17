logger.lifecycle("初始化settings")

rootProject.name = ("MyHub")

include(
    "app",
    "library-shared",
    "module-login",
    "module-main",
    "module-home",
    "module-me"
)
