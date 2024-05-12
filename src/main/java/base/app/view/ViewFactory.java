package base.app.view;

import UI.BoardView;
import base.Size;
import base.app.factory.IArgFactory;
import base.app.storage.StorageConnector;
import base.app.storage.StorageKeeper;
import base.interfaces.IViewStrategy;

public abstract class ViewFactory<TArg, TResult extends View> 
        extends StorageConnector<TResult>
        implements IArgFactory<TArg, TResult> {
    private final BoardView boardView;
    
    public ViewFactory(IViewStrategy gameInstaller, StorageKeeper storageKeeper){
        super(storageKeeper);
        boardView = gameInstaller.getBoardView();
    }
    
    public TResult create(TArg args) throws IllegalArgumentException {
        var viewOptions = getViewOptions();
        var view = createView(args, viewOptions.size());

        addToContainer(view);
        tryPutItem(view);
        
        return view;
    }
    
    protected abstract ViewOptions getViewOptions();
    protected abstract TResult createView(TArg params, Size size);
    
    private void addToContainer(TResult view){
        boardView.addView(view);
    }
}
