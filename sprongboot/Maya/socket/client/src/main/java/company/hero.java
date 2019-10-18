package company;

public class hero{

    private String name;
    private float hp;

    private int damage;

    //回血
    public synchronized void recover(){
        hp=hp+1;
    }

    //掉血
    public void hurt(){
        //使用this作为同步对象
        synchronized (this) {
            hp=hp-1;
        }
    }

    public void attackHero(hero h) {
        h.hp-=damage;
        System.out.format("%s 正在攻击 %s, %s的血变成了 %.0f%n",name,h.name,h.name,h.hp);
        if(h.isDead())
            System.out.println(h.name +"死了！");
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getHp() {
        return hp;
    }

    public void setHp(float hp) {
        this.hp = hp;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isDead() {
        return 0>=hp?true:false;
    }

}