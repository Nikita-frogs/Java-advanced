public interface DiscountPolicy {
    Money apply(Money basePrice);
}

class NoDiscountPolicy implements DiscountPolicy {
    @Override
    public Money apply(Money basePrice) {
        return basePrice;
    }
}

class PercentDiscountPolicy implements DiscountPolicy {
    private final double percent;

    public PercentDiscountPolicy(double percent) {
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("Percent must be 0..100");
        }
        this.percent = percent;
    }

    @Override
    public Money apply(Money basePrice) {
        return basePrice.multiply(1 - percent / 100.0);
    }
}
