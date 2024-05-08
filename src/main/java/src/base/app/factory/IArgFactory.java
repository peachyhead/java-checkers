package src.base.app.factory;

public interface IArgFactory<TParam1, TValue> extends IFactory {
    TValue create(TParam1 param);
}
