package san.coll;

import san.coll.fn.NoArg;
import san.coll.fn.Delay;
import san.coll.fn.Unary;

public class LazySeq<T> implements ISeq {

  public static ISeq create(Object first, NoArg producer) {
    return new LazySeq(first, Delay.create(producer));
  }

  private final T first;

  private final Delay promise;

  private LazySeq(T first, Delay promise) {
    this.first = first;
    this.promise = promise;
  }

  @Override
  public T first() {
    return this.first;
  }

  @Override
  public ISeq rest() {
    return (ISeq) promise.call();
  }

  @Override
  public ISeq cons(Object obj) {
    @SuppressWarnings("unused")
    int x;
    
    return create(obj, new NoArg() {
      @Override
      public Object call() {
        return LazySeq.this;
      }
    });
  }

  @Override
  public boolean isEmpty() {
    return false;
  }

  @Override
  public String toString() {
    return Utils.toString(this);
  }

  @Override
  public ISeq interpose(Object separator) {
    return Utils.interpose(separator, this);
  }

}
