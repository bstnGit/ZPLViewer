package com.bstn.zplviewer.graphics;

import java.io.File;
import java.util.ArrayList;

import com.bstn.zplviewer.util.PDFUtil;

import renderables.Renderable;

public class RenderQueue {
	private ArrayList<Renderable> queue;

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
    public void render(Renderer renderer, File file) {
        for (Renderable renderable : queue) {
            renderable.render(renderer);
        }
        
        if(renderer.save(file)) {
        	PDFUtil.open(file);
        }
    }

	@Override
	public String toString() {
		return "RenderQueue [queue=" + queue + "]";
	}
}
