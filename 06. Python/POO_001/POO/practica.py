from typing import List


def exclusiveTime(n: int, logs: List[str]) -> List[int]:
    res = [0] * n
    stack = []
    prev_time = 0
    for log in logs:
        splited_log = log.split(':')
        if splited_log[1] == "start":
            if stack:
                res[int(splited_log[0])] += int(splited_log[2]) - prev_time
            stack.append(int(splited_log[0]))
            prev_time = int(splited_log[2])
        else:
            n = stack.pop()
            res[n] += int(splited_log[2]) - prev_time +1
            prev_time = int(splited_log[2]) + 1
    return res

if __name__ == "__main__":
    print(exclusiveTime(2, ["0:start:0","0:start:2","0:end:5","1:start:6","1:end:6","0:end:7"]))
