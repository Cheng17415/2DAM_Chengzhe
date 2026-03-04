from typing import List


def smallerNumbersThanCurrent(nums: List[int]):
        sup = [i for i in range(1, len(nums) + 1)]
        res = []
        for s in sup:
            if s not in nums:
                res.append(s)
        return res

if __name__ == "__main__":
    a = [3,1,1,4,5,4]
    print(smallerNumbersThanCurrent(a))
    