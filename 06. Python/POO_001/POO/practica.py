from typing import List


def dailyTemperatures( temperatures: List[int]) -> List[int]:
    res = [0] * len(temperatures)
    stack = []

    for i, t in enumerate(temperatures):
        while stack and t > temperatures[stack[-1]]:
            prev = stack.pop()
            res[prev] = i - prev
        stack.append(i)

    return res

if __name__ == "__main__":
    print(dailyTemperatures([73,74,75,71,69,72,76,73]))
