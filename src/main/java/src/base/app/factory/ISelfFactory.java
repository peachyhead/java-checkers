package src.base.app.factory;

public interface ISelfFactory<TValue> extends IFactory {
    TValue create();
}

