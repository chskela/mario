package jade

import org.lwjgl.Version
import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11.*
import org.lwjgl.system.MemoryUtil.NULL
import java.awt.SystemColor.window


object Window {
    private const val width = 1920
    private const val height = 1080
    private const val title = "Mario"
    private var jlfwWindow = NULL

    fun run() {
        println("Hello LWJGL " + Version.getVersion() + "!");

        init();
        loop();
    }

    fun init() {

        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (!glfwInit()) throw IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE); // the window will be resizable
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE)

        // Create the window
        jlfwWindow = glfwCreateWindow(width, height, title, NULL, NULL);
        if (jlfwWindow == NULL)
            throw RuntimeException("Failed to create the GLFW window");

        // Make the OpenGL context current
        glfwMakeContextCurrent(jlfwWindow);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(jlfwWindow);

        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();
    }

    fun loop() {
        // the window or has pressed the ESCAPE key.
        // the window or has pressed the ESCAPE key.
        while (!glfwWindowShouldClose(jlfwWindow)) {

            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents()

            // Set the clear color
            glClearColor(1.0f, 0.0f, 0.0f, 0.0f);

            glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT) // clear the framebuffer
            glfwSwapBuffers(jlfwWindow) // swap the color buffers
        }
    }
}