// 1622. Fancy Sequence
// Write an API that generates fancy sequences using the append, addAll, and multAll operations.
// Implement the Fancy class:
// Fancy() Initializes the object with an empty sequence.
// void append(val) Appends an integer val to the end of the sequence.
// void addAll(inc) Increments all existing values in the sequence by an integer inc.
// void multAll(m) Multiplies all existing values in the sequence by an integer m.
// int getIndex(idx) Gets the current value at index idx (0-indexed) of the sequence modulo 109 + 7. If the index is greater or equal than the length of the sequence, return -1.
// Example 1:
// Input
// ["Fancy", "append", "addAll", "append", "multAll", "getIndex", "addAll", "append", "multAll", "getIndex", "getIndex", "getIndex"]
// [[], [2], [3], [7], [2], [0], [3], [10], [2], [0], [1], [2]]
// Output
// [null, null, null, null, null, 10, null, null, null, 26, 34, 20]


class Fancy {
    ArrayList<Long> list;
    long MOD = 1_000_000_007L;

    public Fancy() {
        list = new ArrayList<>();
    }
    
    public void append(int val) {
        list.add((long) val);
    }
    
    public void addAll(int inc) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, (list.get(i) + inc) % MOD);
        }
    }
    
    public void multAll(int m) {
        for (int i = 0; i < list.size(); i++) {
            list.set(i, (list.get(i) * m) % MOD);
        }
    }
    
    public int getIndex(int idx) {
        if (idx >= list.size()) return -1;
        return (int)(list.get(idx) % MOD);
    }
}

