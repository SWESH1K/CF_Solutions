
for _ in range(int(input())):

    n, k = map(int, input().split())
    nums = list(map(int, input().split()))
    n = len(nums)
    range_set = dict()

    for num in nums:
        range_set[num] = set([i for i in range(num-k, num+k+1)])
        

    left = 0
    counter = 0

    # for j in range(n): 
    inter = set(list(range_set[nums[left]]))
    for i in range(left+1, n):
        inter &= range_set[nums[i]]
        # print(inter)
        if(len(inter) == 0):
            # print(f"Became empty at left:{left}, i={i}")
            # print("Set left:", range_set[nums[left]], "Set i:", range_set[nums[i]])
            counter+=1
            left=i
            inter = range_set[nums[i]]
            # break

    print(counter)