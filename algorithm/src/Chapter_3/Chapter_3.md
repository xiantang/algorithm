### 符号表

随机命中:查找每个键的可能性都相同的情况下,
结果就是一次查找平均所需要的时间.
平均比较次数: ~N/2 (1+2+3+    +N)/N=(N+1)/2~N/2


### 有序数组的二分查找
```java
public int rank(Key key){
        int lo = 0;
        int hi = N-1;
        while (lo<=hi){
            int mid = (lo+hi)/2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp>0)lo = mid+1;
            else if (cmp<0)hi = mid-1;
            else return mid;
        }
        return lo;
    }
```
这个实现还是很巧妙的 利用的二分法获得一个节点的位置
如果这个节点不存在 就返回比这个节点小的节点的数量
他这个`<=`页很关键 在只剩下一个元素的时候如果这个元素不是目标元素的话
就使`lo`比`hi`大1

```java
public void  put(Key key,Value val){
        int i = rank(key);
        if (i<N && keys[i].compareTo(key) == 0){
            values[i] = val;
            return;
        }
        for (int j = N; j >i ; j--) {
            keys[j] = keys[j-1];values[j] = values[j-1];
        }
        keys[i] = key;values[i] = val;
        N++;
    }
```
如果拿到这个值就给他更新 如果没有就使他之后的元素位移1位
巧妙