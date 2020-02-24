package fm.force.util

import history.Hash
import history.Location
import history.Pathname
import history.Search
import org.w3c.dom.Node

fun <S> createLocation(pathname: Pathname = "/", search: Search = "", hash: Hash = "", state: S? = null) =
    jsApply<Location<S>> {
        this.pathname = pathname
        this.search = search
        this.hash = hash
        this.state = state
    }

class NodeChildIterator(node: Node) : Iterator<Node> {
    private val childIterator = node.childNodes.iterator()
    override fun hasNext() = childIterator.hasNext()
    override fun next() = childIterator.next()
}

class NodeChildTreeIterator(node: Node) : Iterator<Node> {
    private val queue = ArrayDeque(listOf(node))
    private val visited = mutableSetOf<Node>()
    private val valueNodes = ArrayDeque<Node>()

    private fun processHeadQueueNode(): Boolean {
        var currentNode: Node
        while (true) {
            if (queue.size == 0) {
                return false
            }

            currentNode = queue.removeFirst()
            if (currentNode !in visited) {
                break
            }
        }

        valueNodes.add(currentNode)
        visited.add(currentNode)

        for (childNode in currentNode.iterator()) {
            queue.addLast(childNode)
            valueNodes.addLast(childNode)
        }
        return true
    }

    override fun hasNext(): Boolean {
        return valueNodes.size > 0 || processHeadQueueNode()
    }

    override fun next(): Node {
        if (valueNodes.size == 0) {
            processHeadQueueNode()
        }

        return valueNodes.removeFirst()
    }
}

fun Node.iterator() = NodeChildIterator(this)

fun Node.treeIterator() = NodeChildTreeIterator(this)
