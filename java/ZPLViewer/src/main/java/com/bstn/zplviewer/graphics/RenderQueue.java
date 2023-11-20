package com.bstn.zplviewer.graphics;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.bstn.zplviewer.renderables.Renderable;

public class RenderQueue {
	private List<Renderable> queue;

    public RenderQueue() {
        queue = new ArrayList<>();
    }
    
    /**
     * Adds a Renderable element to the end of the render queue.
     *
     * @param renderable The Renderable object to be added to the queue.
     */
    public void addElement(Renderable renderable) {
        queue.add(renderable);
    }

    /**
     * Renders each Renderable object in the queue using the provided Renderer.
     *
     * @param renderer The Renderer used to render each element in the queue.
     */
    public PDFRenderer render(PDFRenderer renderer, File file) {
        for (Renderable renderable : queue) {
            renderable.render(renderer);
        }
        
        return renderer;
    }

	@Override
	public String toString() {
		return "RenderQueue [queue=" + queue + "]";
	}
}
