class Solution {
    public long solution(int cap, int n, int[] deliveries, int[] pickups) {
        /** 
        deliveries를 끝에서부터 돌면서, cap을 채운다. => deliveries 배열 업데이트.
        pickups를 끝에서부터 돌면서, cap을 채운다. => pickups 배열 업데이트.
        => 반복
        */
        int deliveryGoal = 0;
        int pickupGoal = 0;
        for (int d : deliveries) {
            deliveryGoal += d;
        }
        for (int p : pickups) {
            pickupGoal += p;
        }
        long moved = 0;
        int totalDelivery = 0;
        int totalPickup = 0;
        int farthestHouseThatMightHavePackages = n-1;
        int farthestHouseThatMightHavePickup= n-1;
        while (totalDelivery < deliveryGoal || totalPickup < pickupGoal) {
            int capacity = cap;
            int maxDistHouse = 0;
            for (int i = farthestHouseThatMightHavePackages; i >= 0; i --) {
                int boxToDeliver = deliveries[i];
                if (boxToDeliver == 0) {
                    farthestHouseThatMightHavePackages = i-1;
                    continue;
                }
                if (maxDistHouse == 0) {
                    maxDistHouse = i+1;
                }
                if (boxToDeliver < capacity) {
                    // System.out.println("Delivered to house number " + i + ": " + box + "boxes." );
                    totalDelivery += boxToDeliver;
                    capacity -= boxToDeliver;
                    deliveries[i] = 0;
                    farthestHouseThatMightHavePackages = i-1;
                } else if(boxToDeliver == capacity) {
                    deliveries[i] = 0;
                    capacity = 0;
                    totalDelivery += boxToDeliver;
                    farthestHouseThatMightHavePackages = i-1;
                    break;
                } else {
                    // System.out.println("Delivered to house number " + i + ": " + capacity + "boxes." );
                    deliveries[i] = boxToDeliver - capacity;
                    totalDelivery += capacity;
                    break;
                }
            }
            capacity = cap;
            for (int i = farthestHouseThatMightHavePickup; i >= 0; i --) {
                int boxToPickup = pickups[i];
                if (boxToPickup == 0) {
                    farthestHouseThatMightHavePickup = i-1;
                    continue;
                }
                maxDistHouse = Math.max(maxDistHouse, i+1);
                if (boxToPickup < capacity) {
                    totalPickup += boxToPickup;
                    capacity -= boxToPickup;
                    pickups[i] = 0;
                    farthestHouseThatMightHavePickup = i-1;
                } else if(boxToPickup == capacity) {
                    pickups[i] = 0;
                    capacity = 0;
                    totalPickup += boxToPickup;
                    farthestHouseThatMightHavePickup = i-1;
                    break;
                } else {
                    pickups[i] = boxToPickup - capacity;
                    totalPickup += capacity;
                    break;
                }
            }
            moved += maxDistHouse * 2;
            // System.out.println("Moved " + maxDistHouse * 2);
        }
        return moved;
    }
}