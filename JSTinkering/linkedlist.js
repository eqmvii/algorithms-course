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

function Quu(){
    this.first = null; 
}

Quu.push = function(newString){
    var oldFirst = this.first;
    this.first = new stringListNode(newString);
    this.first.next = oldFirst;   
}

Quu.printAll = function(){
    var crawler = this.first;
    while (crawler.next != null){
        console.log(crawler.payload);
        crawler = crawler.next;
    }
    console.log(crawler.payload);
}

Quu.pop = function(){
    this.first = this.first.next;
}

function main() {
    console.log("Main function called...");
    console.log(" ");

    /*
    var firstNode = new stringListNode("Hello!");
    // at this point it's just one node and it is pointing to null
    var secondNode = new stringListNode("Eric");
    firstNode.next = secondNode;
    var thirdNode = new stringListNode("3rd string!");
    secondNode.next = thirdNode;
    var fourthNode = new stringListNode("Fourth string here!");
    thirdNode.next = fourthNode;
    firstNode.printMyChain();
    */

    var myQ = new Quu();
    Quu.push("Hello");
    Quu.push("Eric!");
    Quu.push("how");
    Quu.push("are");
    Quu.push("you?");
    Quu.printAll();
    console.log("- - - - pop - - - - -");
    Quu.pop();
    Quu.printAll();


}

main();