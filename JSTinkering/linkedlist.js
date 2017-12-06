// linkedlist.js
// messing with linked list implemntation in javascript

// console.log("Link Success!");

function stringListNode(karl) {
    this.next = null;
    this.payload = karl;

    this.printMyString = function () {
        console.log(this.payload);
    }

    this.printMyChain = function () {
        var crawler = this;

        while (crawler.next != null) {
            crawler.printMyString();
            crawler = crawler.next;
        }
        crawler.printMyString();
    }
}

function main() {
    console.log("Main function called...");
    console.log(" ");
    var firstNode = new stringListNode("Hello!");
    // at this point it's just one node and it is pointing to null
    var secondNode = new stringListNode("Eric");
    firstNode.next = secondNode;
    var thirdNode = new stringListNode("3rd string!");
    secondNode.next = thirdNode;
    var fourthNode = new stringListNode("Fourth string here!");
    thirdNode.next = fourthNode;

    firstNode.printMyChain();


}

main();