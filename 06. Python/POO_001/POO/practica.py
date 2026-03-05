from typing import List


def findErrorNums(nums: List[int]) -> List[int]:
    seen = set()
    dupe = -1
    for num in nums:
        if num in seen:
            dupe = num
        seen.add(num)
    missing =
    return [dupe,]

if __name__ == "__main__":
    print(findErrorNums([3,2,2]))