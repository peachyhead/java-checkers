package src.base.app.view;

import src.UI.MainFrame;
import src.base.Size;
import src.base.app.factory.IArgFactory;
import src.base.app.storage.StorageKeeper;
import src.base.app.storage.StorageConnector;

public abstract class ViewFactory<TArg, TResult extends View> 
        extends StorageConnector<TResult>
        implements IArgFactory<TArg, TResult> {
    private final MainFrame mainFrame;
    
    public ViewFactory(MainFrame frame, StorageKeeper storageKeeper){
        super(storageKeeper);
        this.mainFrame = frame;
    }
    
    public TResult create(TArg args) throws IllegalArgumentException {
        var viewOptions = getViewOptions();
        var view = createView(args, viewOptions.size());

        addToContainer(view);
        tryPutItem(view);
        view.setVisible(true);
        
        return view;
    }
    
    protected abstract ViewOptions getViewOptions();
    protected abstract TResult createView(TArg params, Size size);
    
    private void addToContainer(TResult view){
        mainFrame.addView(view);
    }
}
