object GradleConstants {
    private const val GRADLE_MAIN_PACKAGE = "anzid_gradle/"

    const val SHARED_BUILD_TYPES_GRADLE_PATH = "${GRADLE_MAIN_PACKAGE}shared_build_types.gradle"
    const val SHARED_PRODUCT_FLAVORS_GRADLE_PATH = "${GRADLE_MAIN_PACKAGE}shared_product_flavors.gradle"
    const val SHARED_COMPILE_OPTIONS_GRADLE_PATH = "${GRADLE_MAIN_PACKAGE}shared_compile_options.gradle"
    const val SHARED_GRADLE_FOR_ANDROID_LIB_PATH = "${GRADLE_MAIN_PACKAGE}shared_gradle_configuration_for_adroid_lib.gradle"

    const val SHARED_DEPENDENCIES_GRADLE_PATH = "${GRADLE_MAIN_PACKAGE}deps/shared_dependencies.gradle"
    const val DAGGER_DEPENDENCIES_GRADLE_PATH = "${GRADLE_MAIN_PACKAGE}deps/dagger_dependencies.gradle"
    const val ROOM_DEPENDENCIES_GRADLE_PATH = "${GRADLE_MAIN_PACKAGE}deps/room_dependencies.gradle"
}