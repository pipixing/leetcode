public class Stack {
    public static class Stack2Queue {
        java.util.Stack<Integer> stack1 = new java.util.Stack<>();
        java.util.Stack<Integer> stack2 = new java.util.Stack<>();

        /**
         * Initialize your data structure here.
         */
        public Stack2Queue() {

        }

        /**
         * Push element x to the back of queue.
         */
        public void push(int x) {
            stack1.push(x);
        }

        /**
         * Removes the element from in front of queue and returns that element.
         */
        public int pop() {
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
                return stack2.pop();
            } else {
                return stack2.pop();
            }
        }

        /**
         * Get the front element.
         */
        public int peek() {
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
                return stack2.peek();
            } else {
                return stack2.peek();
            }
        }

        /**
         * Returns whether the queue is empty.
         */
        public boolean empty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }
    }

    static class MinStack {

        private java.util.Stack<Integer> stack = new java.util.Stack<>();
        private java.util.Stack<Integer> minStack = new java.util.Stack<>();

        /**
         * @Author: PPX
         * @Description:最小栈
         * @Date: 2019-09-27
         * @return: null
         **/
        public MinStack() {

        }

        public void push(int x) {
            stack.push(x);
            minStack.push(minStack.isEmpty() ? x : Math.min(x, minStack.peek()));
        }

        public void pop() {
            if (!stack.isEmpty()) {
                stack.pop();
                minStack.pop();
            } else {
                System.out.println("The Stack is Empty!");
            }
        }

        public int top() {
            return stack.isEmpty() ? 0 : stack.peek();
        }

        public int getMin() {
            return minStack.isEmpty() ? 0 : minStack.peek();
        }
    }
}
